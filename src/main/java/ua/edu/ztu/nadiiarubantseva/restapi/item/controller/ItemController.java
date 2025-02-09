package ua.edu.ztu.nadiiarubantseva.restapi.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.edu.ztu.nadiiarubantseva.restapi.common.ApiResponse;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemCreateRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemDTO;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemPutRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.dto.ItemUpdateRequest;
import ua.edu.ztu.nadiiarubantseva.restapi.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@Validated
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority(" +
                  "T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).ADMIN.name()," +
                  "T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).USER.name())")
    public ResponseEntity<ApiResponse<List<ItemDTO>, ?>> getAllItems() {
        return ResponseEntity.ok(ApiResponse.success(itemService.getAllItems()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).ADMIN.name())")
    public ResponseEntity<ApiResponse<ItemDTO, ?>> createItem(@RequestBody ItemCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(itemService.createItem(request)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(" +
                  "T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).ADMIN.name()," +
                  "T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).USER.name())")
    public ResponseEntity<ApiResponse<ItemDTO, ?>> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(itemService.getItemById(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).ADMIN.name())")
    public ResponseEntity<ApiResponse<ItemDTO, ?>> replaceItem(@PathVariable Long id,
                                                               @RequestBody ItemPutRequest request) {
        return ResponseEntity.ok(ApiResponse.success(itemService.replaceItem(id, request)));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority(T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).ADMIN.name())")
    public ResponseEntity<ApiResponse<ItemDTO, ?>> updateItem(@PathVariable Long id,
                                                              @RequestBody ItemUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(itemService.updateItem(id, request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(T(ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role).ADMIN.name())")
    public ResponseEntity<ApiResponse<String, ?>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(ApiResponse.success("Item deleted successfully"));
    }
}
