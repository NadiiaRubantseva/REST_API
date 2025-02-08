package ua.edu.ztu.nadiiarubantseva.restapi.item;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();
    }

    public static Item toEntity(ItemDTO dto) {
        return Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}
