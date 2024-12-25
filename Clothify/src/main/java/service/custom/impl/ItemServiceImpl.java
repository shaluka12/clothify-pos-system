package service.custom.impl;

import dto.Item;
import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import service.custom.ItemService;
import util.DaoType;

public class ItemServiceImpl implements ItemService {

    public ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);

    @Override
    public boolean addItem(Item item) {
        return itemDao.save(new ModelMapper().map(item, ItemEntity.class));
    }

    @Override
    public boolean deleteItem(String id) {
        return itemDao.delete(id);
    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<ItemEntity> itemEntities = itemDao.getAll();
        ObservableList<Item> items = FXCollections.observableArrayList();
        itemEntities.forEach(itemEntity -> items.add(new ModelMapper().map(itemEntity,Item.class)));

        return items;
    }

    @Override
    public boolean updateItem(Item item) {
        return itemDao.update(new ModelMapper().map(item, ItemEntity.class));
    }

    @Override
    public Item searchItem(String id) {
        return new ModelMapper().map(itemDao.search(id), Item.class);
    }

    @Override
    public ObservableList<String> getItemIds() {
        return null;
    }
}
