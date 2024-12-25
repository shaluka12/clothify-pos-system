package repository;

import repository.custom.impl.*;
import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case ITEM:return (T) new ItemDaoImpl();
            case ORDER_DETAILS:return (T) new OrderDetailsDaoImpl();
            case ORDER:return (T) new OrdersDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case USER:return (T) new UserDaoImpl();
        }
        return null;
    }
}
