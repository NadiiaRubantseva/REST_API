package ua.edu.ztu.nadiiarubantseva.restapi.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.ztu.nadiiarubantseva.restapi.common.ApiException;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemDTO;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemPutRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemUpdateRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.mapper.ItemMapper;
import ua.edu.ztu.nadiiarubantseva.restapi.item.model.Item;
import ua.edu.ztu.nadiiarubantseva.restapi.item.validator.ItemErrorCode;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemCreateRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.repository.ItemRepository;
import ua.edu.ztu.nadiiarubantseva.restapi.item.validator.ItemValidator;

import java.util.List;
import java.util.Optional;

import static ua.edu.ztu.nadiiarubantseva.restapi.item.mapper.ItemMapper.*;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemValidator itemValidator;
    private final ItemRepository itemRepository;

    public ItemDTO createItem(ItemCreateRequest request) {
        Optional<ItemErrorCode> errorCode = itemValidator.validate(request);
        if (errorCode.isPresent()) {
            throw new ApiException(errorCode.get());
        }
        Item item = itemRepository.save(mapToEntity(request));
        return mapToDTO(item);
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(ItemMapper::mapToDTO)
                .toList();
    }

    public ItemDTO getItemById(Long id) {
        return itemRepository.findById(id)
                .map(ItemMapper::mapToDTO)
                .orElseThrow(() -> new ApiException(ItemErrorCode.ITEM_NOT_FOUND));
    }

    public ItemDTO updateItem(Long id, ItemUpdateRequest request) {
        Optional<ItemErrorCode> errorCode = itemValidator.validate(id, request);
        if (errorCode.isPresent()) {
            throw new ApiException(errorCode.get());
        }
        Item existingItem = itemRepository.findById(id).orElseThrow();
        if (request.name() != null) {
            existingItem.setName(request.name());
        }
        if (request.description() != null) {
            existingItem.setDescription(request.description());
        }
        if (request.price() != null) {
            existingItem.setPrice(request.price());
        }
        return mapToDTO(itemRepository.save(existingItem));
    }

    public void deleteItem(Long id) {
        Optional<ItemErrorCode> errorCode = itemValidator.validate(id);
        if (errorCode.isPresent()) {
            throw new ApiException(errorCode.get());
        }
        itemRepository.deleteById(id);
    }

    public ItemDTO replaceItem(Long id, ItemPutRequest request) {
        Optional<ItemErrorCode> errorCode = itemValidator.validate(id, request);
        if (errorCode.isPresent()) {
            throw new ApiException(errorCode.get());
        }
        if (itemRepository.existsById(id)) {
            Item existingItem = itemRepository.findById(id).orElseThrow();
            existingItem.setName(request.name());
            existingItem.setDescription(request.description());
            existingItem.setPrice(request.price());
            return mapToDTO(itemRepository.save(existingItem));
        } else {
            Item item = itemRepository.save(mapToEntity(id, request));
            return mapToDTO(item);
        }
    }
}
