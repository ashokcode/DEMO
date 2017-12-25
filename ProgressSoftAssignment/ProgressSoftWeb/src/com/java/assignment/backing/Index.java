package com.java.assignment.backing;


import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;

public class Index {


    private RichDocument d1;
    private RichForm f1;
    private RichOutputText ot1;
    private RichButton b1;
    private RichOutputText ot2;
    private DCIteratorBinding successIter;
    private DCIteratorBinding failureIter;
    private DCIteratorBinding countIter;

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }


    public void setSuccessIter(DCIteratorBinding successIter) {
        this.successIter = successIter;
    }

    public DCIteratorBinding getSuccessIter() {
        return successIter;
    }

    public void setFailureIter(DCIteratorBinding failureIter) {
        this.failureIter = failureIter;
    }

    public DCIteratorBinding getFailureIter() {
        return failureIter;
    }

    public void setCountIter(DCIteratorBinding countIter) {
        this.countIter = countIter;
    }

    public DCIteratorBinding getCountIter() {
        return countIter;
    }

    public boolean getShowTab() {

        ADFContext ctx = ADFContext.getCurrent();
        Map sessionMap=ctx.getSessionScope();
        
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("transactionSuccessFindAllIterator");
        setSuccessIter(iter);
        sessionMap.put("transactionSuccessFindAllIterator", getSuccessIter());
        System.out.println("\n\n Iterator found1:" + iter.size());
        iter = (DCIteratorBinding) bindings.get("transactionFailedFindAllIterator");
        setFailureIter(iter);
        sessionMap.put("transactionFailedFindAllIterator", getFailureIter());
    //    System.out.println("\n\n Iterator found2:" + iter.size());
        iter = (DCIteratorBinding) bindings.get("transactionCountsFindAllIterator");
        setCountIter(iter);
        sessionMap.put("transactionCountsFindAllIterator", getCountIter());
     //   System.out.println("\n\n Iterator found3:" + iter.size());

        return true;
    }

}
