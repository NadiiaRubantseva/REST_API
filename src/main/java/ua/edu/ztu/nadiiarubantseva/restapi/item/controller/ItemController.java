package ua.edu.ztu.nadiiarubantseva.restapi.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ApiResponse<Item, ?>> createItem(@RequestBody ItemRequestDto request) {
        return ResponseEntity.ok(ApiResponse.success(itemService.createItem(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Item>, ?>> getAllItems() {
        return ResponseEntity.ok(ApiResponse.success(itemService.getAllItems()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Item, ItemErrorCode>> getItemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(itemService.getItemById(id)));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(ApiResponse.failure(e.getErrorCode()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Item, ItemErrorCode>> updateItem(@PathVariable Long id, @RequestBody ItemRequestDto request) {
        try {
            return ResponseEntity.ok(ApiResponse.success(itemService.updateItem(id, request)));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(ApiResponse.failure(e.getErrorCode()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void, ItemErrorCode>> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(ApiResponse.failure(e.getErrorCode()));
        }
    }
}
