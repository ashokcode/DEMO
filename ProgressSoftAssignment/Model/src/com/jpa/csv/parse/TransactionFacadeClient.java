package com.jpa.csv.parse;

import java.util.List;

public class TransactionFacadeClient {
    public static void main(String[] args) {
        try {
            final TransactionFacade transactionFacade = new TransactionFacade();
            for (TransactionSuccess transactionsuccess :
                 (List<TransactionSuccess>) transactionFacade.getTransactionSuccessFindAll()) {
                printTransactionSuccess(transactionsuccess);
            }
            for (TransactionFailed transactionfailed :
                 (List<TransactionFailed>) transactionFacade.getTransactionFailedFindAll()) {
                printTransactionFailed(transactionfailed);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printTransactionSuccess(TransactionSuccess transactionsuccess) {
        System.out.println("amount = " + transactionsuccess.getAmount());
        System.out.println("filename = " + transactionsuccess.getFilename());
        System.out.println("orderingcurrency = " + transactionsuccess.getOrderingcurrency());
        System.out.println("tocurrency = " + transactionsuccess.getTocurrency());
        System.out.println("transactiontime = " + transactionsuccess.getTransactiontime());
        System.out.println("uid = " + transactionsuccess.getUid());
    }

    private static void printTransactionFailed(TransactionFailed transactionfailed) {
        System.out.println("failedRow = " + transactionfailed.getFailedRow());
        System.out.println("fileName = " + transactionfailed.getFileName());
        System.out.println("remarks = " + transactionfailed.getRemarks());
        System.out.println("uid = " + transactionfailed.getUid());
    }
}
