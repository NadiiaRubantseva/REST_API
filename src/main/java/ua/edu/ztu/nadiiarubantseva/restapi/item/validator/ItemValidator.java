package ua.edu.ztu.nadiiarubantseva.restapi.item.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.edu.ztu.nadiiarubantseva.restapi.common.ApiException;
import ua.edu.ztu.nadiiarubantseva.restapi.common.StringUtil;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemCreateRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemPutRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemUpdateRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.repository.ItemRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemValidator {

    private final ItemRepository itemRepository;

    public Optional<ItemErrorCode> validate(ItemCreateRequest request) {
        if (request == null) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_DATA);
        }
        if (itemRepository.existsByName(request.name())) {
            throw new ApiException(ItemErrorCode.ITEM_ALREADY_EXISTS);
        }
        return validateCommonFields(request.name(), request.description(), request.price());
    }

    public Optional<ItemErrorCode> validate(Long id, ItemUpdateRequest request) {
        if (request == null) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_DATA);
        }
        if (id == null || !itemRepository.existsById(id)) {
            throw new ApiException(ItemErrorCode.ITEM_NOT_FOUND);
        }
        if (request.name() != null && StringUtil.isBlank(request.name())) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_NAME);
        }
        if (request.description() != null && StringUtil.isBlank(request.description())) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_DESCRIPTION);
        }
        if (request.price() != null && request.price() < 0) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_PRICE);
        }
        return Optional.empty();
    }

    public Optional<ItemErrorCode> validate(Long id, ItemPutRequest request) {
        if (request == null) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_DATA);
        }
        if (id != null && !itemRepository.existsById(id)) {
            throw new ApiException(ItemErrorCode.ITEM_NOT_FOUND);
        }
        return validateCommonFields(request.name(), request.description(), request.price());
    }
    
    public Optional<ItemErrorCode> validate(Long id) {
        if (id == null || !itemRepository.existsById(id)) {
            throw new ApiException(ItemErrorCode.ITEM_NOT_FOUND);
        }
        return Optional.empty();
    }

    private Optional<ItemErrorCode> validateCommonFields(
            String name,
            String description,
            Double price
    ) {
        if (StringUtil.isBlank(name)) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_NAME);
        }
        if (StringUtil.isBlank(description)) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_DESCRIPTION);
        }
        if (price == null || price < 0) {
            return Optional.of(ItemErrorCode.INVALID_ITEM_PRICE);
        }
        return Optional.empty();
    }
}
