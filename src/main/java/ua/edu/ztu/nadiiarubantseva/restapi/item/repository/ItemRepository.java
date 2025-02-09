package ua.edu.ztu.nadiiarubantseva.restapi.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ztu.nadiiarubantseva.restapi.item.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);
    boolean existsById(Long id);
}
