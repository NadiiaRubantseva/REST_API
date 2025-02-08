package ua.edu.ztu.nadiiarubantseva.restapi.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item createItem(ItemRequestDto request) {
        if (itemRepository.existsByName(request.name())) {
            throw new ItemNotFoundException(ItemErrorCode.ITEM_ALREADY_EXISTS);
        }

        Item item = Item.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();

        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(ItemErrorCode.ITEM_NOT_FOUND));
    }

    public Item updateItem(Long id, ItemRequestDto request) {
        Item existingItem = getItemById(id);
        existingItem.setName(request.name());
        existingItem.setDescription(request.description());
        existingItem.setPrice(request.price());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }
}
