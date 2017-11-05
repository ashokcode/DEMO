package com.java.assignment.backing;

import com.jpa.csv.parse.TransactionFacadeClient;
import com.jpa.csv.parse.TransactionFailed;
import com.jpa.csv.parse.TransactionSuccess;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class Edit_trasaction {
    private RichForm f1;
    private RichDocument d1;
    private RichInputFile if1;
    private RichButton b1;
    //Variable to store list of Unique UIDs
    private HashSet<String>   uidset=new HashSet<String>();
    //Global entity to pass value
    com.jpa.csv.parse.TransactionSuccess success=new com.jpa.csv.parse.TransactionSuccess();
    com.jpa.csv.parse.TransactionFailed failed=new com.jpa.csv.parse.TransactionFailed();
    
    public Edit_trasaction(){
        try{
        //Populate the uid list from the DB
        ArrayList<String> uid=new ArrayList<String>();
        EntityManager em = Persistence.createEntityManagerFactory((String)"Model1-Outside").createEntityManager();
        List<com.jpa.csv.parse.TransactionSuccess> successlist = em.createNamedQuery("TransactionSuccess.findAll", com.jpa.csv.parse.TransactionSuccess.class).getResultList();
        for (com.jpa.csv.parse.TransactionSuccess sucss : successlist) {
            uid.add(sucss.getUid());
        }
           
        uidset.addAll(uid);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setIf1(RichInputFile if1) {
        this.if1 = if1;
    }

    public RichInputFile getIf1() {
        return if1;
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }


    /**
     * Method to search file already uploaded or not
     * @param valueChangeEvent
     */
    public void validateFile(ValueChangeEvent valueChangeEvent){
        //Get uploaded file
        UploadedFile inputfile = (UploadedFile) valueChangeEvent.getNewValue();
        String fileNameInput=null;
        if(inputfile!=null){
            System.out.println("\n\n\n Input file name: "+inputfile.getFilename());
            fileNameInput=inputfile.getFilename();
        }
        //Initialize the database connection and create query
        EntityManager em = Persistence.createEntityManagerFactory("Model1-Outside").createEntityManager();
       List<TransactionSuccess> successlist= em.createQuery("select o from transaction_success o WHERE o.filename = :filename").setParameter("filename", fileNameInput).getResultList();

        System.out.println(" List Size :"+successlist.size());
        
        //Show error message if file exists
        if(successlist.size()>0){
            String messageText="File with name ["+fileNameInput+"] Already uploaded";
                   FacesMessage fm = new FacesMessage(messageText);
                   fm.setSeverity(FacesMessage.SEVERITY_WARN);
                   FacesContext context = FacesContext.getCurrentInstance();
                   context.addMessage(getIf1().getId(), fm);
        }else {
           
            String path = updateFileTransaction(inputfile);
        }

    }

    /**
     * Method to populate DB with File input
     * @param actionEvent
     */
    public String updateFileTransaction(UploadedFile inputfile)  {
        
        //Initialize the database connection and create query
        EntityManager em = Persistence.createEntityManagerFactory("Model1-Outside").createEntityManager();
        String fileNameInput=inputfile.getFilename();
        try{
        BufferedReader inputread=new BufferedReader(new InputStreamReader(inputfile.getInputStream(), "UTF-8"));
        String line=null;    
        String[] inputRow=null;    
        boolean validRow=false;    
                while ((line = inputread.readLine()) != null) {
                    System.out.println("\n"+line);
                    inputRow=line.split(",");
                     success=new com.jpa.csv.parse.TransactionSuccess();
                     failed=new com.jpa.csv.parse.TransactionFailed();
                   validRow = validateRow(success,failed,inputRow,fileNameInput,validRow);
                    if(validRow){
                        success.setTransactiontime(new Timestamp(new Date().getTime()));
                        success.setFilename(fileNameInput);
                        em.persist(success);
                    }else{
                        failed.setFailedRow(inputRow[0]);
                        failed.setFileName(fileNameInput);
                        
                        em.persist(failed);
                    }
                    
                }
            em.getTransaction().begin();
            em.flush();
            em.getTransaction().commit();
            em.close();
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return  null;
    }


    /**
     * Method to validate the input row from the file and populate the error message for the row
     * @param success
     * @param failed
     * @param inputRow
     * @param fileNameInput
     * @param validRow
     */
    @SuppressWarnings("unchecked")
    private boolean validateRow(TransactionSuccess success, TransactionFailed failed, String[] inputRow,
                             String fileNameInput, boolean validRow) {
        //First validation if the row is not empty
        if (inputRow.length < 4) {
            validRow = false;

            failed.setRemarks("Invalid row in the input file");

        } else {
            //second validation if uid is valid or not
            if (validateUID(inputRow[0])) {
                success.setUid(inputRow[0]);
                uidset.add(inputRow[0]);
                validRow = true;
            } else {
                validRow = false;
                failed.setRemarks("Invalid UID for the row");
                return validRow;
            }
            //Third validation if currency code is valid or not
            if (validateCurrency(inputRow[1])) {
                success.setOrderingcurrency(inputRow[1]);                
                validRow = true;

            }else {
                validRow = false;
                failed.setRemarks("Invalid currency "+inputRow[1]);
                return validRow;
            }           
            //Fourth validation if currency code is valid or not
            if (validateCurrency(inputRow[2])) {
                success.setTocurrency(inputRow[2]);                
                validRow = true;

            }else {
                validRow = false;
                failed.setRemarks("Invalid currency "+inputRow[2]);
                return validRow;
            }
            //Fifth validation if amount is valid
            if (validateAmount(inputRow[3])) {
                success.setAmount(Long.parseLong( inputRow[3]));                
                validRow = true;

            }else {
                validRow = false;
                failed.setRemarks("Invalid amount "+inputRow[3]);
                return validRow;
            }
        }

        if (!validRow) {
            failed.setFailedRow(inputRow[0]);
            failed.setFileName(fileNameInput);
        }
        
        return validRow;
    }

    /**
     * Method to validate the UID from the file
     * @param uid
     * @return
     */
    private boolean validateUID(String uid) {
        boolean validation=false;
        try{
        if(uid.length()>3 && uid.length()<9){
            int id=Integer.parseInt(uid);
            if(id>1000000){
                if(!uidset.contains(uid))
                 validation=true; 
            }
        }
            } finally{
                return validation;
            }
        
    }

    /**
     * Method to validate the currency code for the row
     * @param string
     * @return
     */
    private boolean validateCurrency(String currencyCode) {
        boolean validation = false;
        try {
            if (Currency.getInstance(currencyCode) != null) {
                validation = true;
            }
        } finally {
            return validation;
        }


    }

    /**
     * Method to validate the amount
     * @param string
     * @return
     */
    private boolean validateAmount(String amt) {
        boolean validation = false;
        try {
            int value=Integer.parseInt(amt);
            if (value > 1) {
                validation = true;
            }
        } finally {
            return validation;
        }
    }

    public void updateFileTransaction(ActionEvent actionEvent) {
        return;
    }
}
