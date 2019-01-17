package lk.ijse.dep.individualProject.business;

import lk.ijse.dep.individualProject.business.custom.impl.*;
import lk.ijse.dep.individualProject.dao.custom.impl.AccountDAOImpl;
import lk.ijse.dep.individualProject.dao.custom.impl.TransactionDAOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    public enum BOType{
        AccountBO,CATEGORYBO,SUBCATEGORYBO,TRANSACTION,REPORT
    }

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }

        return boFactory;

    }

    public <T extends SuperBO> T getBO(BOType type){
        switch (type){
            case CATEGORYBO:
                return  (T) new CategoryBOImpl();
            case SUBCATEGORYBO:
                return (T) new SubCategoryBOImpl();
            case AccountBO:
                return (T) new AccountBOImpl();
            case TRANSACTION:
                return (T) new TransactionBOImpl();
            case REPORT:
                return (T) new ReportBOImpl();
            default:
                return null;
        }
    }

}
