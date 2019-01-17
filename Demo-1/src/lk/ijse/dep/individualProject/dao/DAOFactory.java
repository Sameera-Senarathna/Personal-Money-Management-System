package lk.ijse.dep.individualProject.dao;

import lk.ijse.dep.individualProject.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public enum DAOType{
        ACCOUNT,CATEGORY,SUBCATEGORY,TRANSACTION,QUERY;
    }

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        if(daoFactory==null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOType type){
        switch (type){
            case ACCOUNT:
                return (T) new AccountDAOImpl();
            case CATEGORY:
                return (T) new CategoryDAOImpl();
            case SUBCATEGORY:
                return (T) new SubCategoryDAOImpl();
            case TRANSACTION:
                return (T) new TransactionDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }

}
