package service;

import service.custom.impl.*;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case ITEM:return (T) new ItemServiceImpl();
            case ORDER_DETAILS:return (T) new OrderDetailsServiceImpl();
            case ORDER:return (T) new OrdersServiceImpl();
            case SUPPLIER:return (T) new SupplierServiceImpl();
            case USER:return (T) new UserServiceImpl();
        }
        return null;
    }
}
