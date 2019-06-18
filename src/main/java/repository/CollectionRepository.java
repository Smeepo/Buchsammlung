package repository;

import model.Collection;
import model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Collection findFirstByName(String name);

    List<Collection> findAllByOwner(Person user);
}
