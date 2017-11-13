package com.java.assignment.backing;



import javax.faces.context.FacesContext;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import org.apache.myfaces.trinidad.context.RequestContext;

public class Trans_succes {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelTabbed pt1;
    private RichShowDetailItem succestab;
    private RichShowDetailItem failtab;
    private RichShowDetailItem counttab;
    private RichMessages m1;
    private RichTable t1;
    private RichInputDate id1;
    private RichButton b1;
    private RichTable t2;
    private RichPanelStretchLayout psl1;
    private RichTable t3;

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

    public void setPt1(RichPanelTabbed pt1) {
        this.pt1 = pt1;
    }

    public RichPanelTabbed getPt1() {
        return pt1;
    }

    public void setSuccestab(RichShowDetailItem succestab) {
        this.succestab = succestab;
    }

    public RichShowDetailItem getSuccestab() {
        System.out.println("before Table Refereshed \n\n\n ");
        //RequestContext.getCurrentInstance().addPartialTarget(t1);
        System.out.println("Table Refereshed \n\n\n ");
        return succestab;
    }

    public void setFailtab(RichShowDetailItem failtab) {
        this.failtab = failtab;
    }

    public RichShowDetailItem getFailtab() {
        return failtab;
    }
    
    public void setCounttab(RichShowDetailItem counttab) {
        this.counttab = counttab;
    }

    public RichShowDetailItem getCounttab() {
        return counttab;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setT3(RichTable t3) {
        this.t3 = t3;
    }

    public RichTable getT3() {
        return t3;
    }
}
