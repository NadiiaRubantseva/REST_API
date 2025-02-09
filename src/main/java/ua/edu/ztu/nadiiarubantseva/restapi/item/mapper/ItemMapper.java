package ua.edu.ztu.nadiiarubantseva.restapi.item.mapper;

import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemCreateRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemDTO;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemPutRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.model.Item;

public class ItemMapper {
    
    public static Item mapToEntity(ItemCreateRequest request) {
        return Item.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
    }

    public static Item mapToEntity(Long id, ItemPutRequest request) {
        return Item.builder()
                .id(id)
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
    }

    public static ItemDTO mapToDTO(Item item) {
        return new ItemDTO(
                item.getId(), 
                item.getName(), 
                item.getDescription(), 
                item.getPrice()
        );
    }
}
