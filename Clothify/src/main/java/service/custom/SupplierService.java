package service.custom;

import dto.Supplier;
import javafx.collections.ObservableList;
import repository.CrudDao;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    boolean deleteSupplier(String id);
    ObservableList<Supplier> getAll();
    boolean updateSupplier(Supplier supplier);
    Supplier searchSupplier(String id);
    ObservableList<Integer> getSupplierIds();
}
