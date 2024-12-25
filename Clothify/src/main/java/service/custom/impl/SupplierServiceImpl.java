package service.custom.impl;

import dto.Supplier;
import dto.User;
import entity.SupplierEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import repository.custom.UserDao;
import service.custom.SupplierService;
import util.DaoType;

public class SupplierServiceImpl implements SupplierService {

    public SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

    @Override
    public boolean addSupplier(Supplier supplier) {
        return supplierDao.save(new ModelMapper().map(supplier, SupplierEntity.class));
    }

    @Override
    public boolean deleteSupplier(String id) {
        return supplierDao.delete(id);
    }

    @Override
    public ObservableList<Supplier> getAll() {
        ObservableList<SupplierEntity> supplierEntities = supplierDao.getAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        supplierEntities.forEach(supplerEntity -> suppliers.add(new ModelMapper().map(supplerEntity,Supplier.class)));

        return suppliers;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return supplierDao.update(new ModelMapper().map(supplier, SupplierEntity.class));
    }

    @Override
    public Supplier searchSupplier(String id) {
        return new ModelMapper().map(supplierDao.search(id), Supplier.class);
    }

    @Override
    public ObservableList<Integer> getSupplierIds() {
        ObservableList<Integer> itemCodes = FXCollections.observableArrayList();
        ObservableList<Supplier> all = getAll();

        all.forEach(supplier->{
            itemCodes.add(supplier.getId());
        });
        return itemCodes;
    }
}
