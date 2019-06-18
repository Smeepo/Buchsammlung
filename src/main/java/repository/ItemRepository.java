package repository;

import model.Collection;
import model.Item;
import model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByType(ItemType type);
    List<Item> findAllByCollectionAndRentedByIsNull(Collection collection);
    List<Item> findAllByRentedByIsNotNull();

    List<Item> findAllByCollection(Collection collection);
}
