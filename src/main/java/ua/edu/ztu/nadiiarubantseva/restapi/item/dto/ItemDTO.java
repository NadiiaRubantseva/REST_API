package ua.edu.ztu.nadiiarubantseva.restapi.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
}