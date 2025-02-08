package ua.edu.ztu.nadiiarubantseva.restapi.item.dto;

public record ItemUpdateRequest(long id, String name, String description, double price) {}
