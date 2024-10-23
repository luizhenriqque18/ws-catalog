package io.github.luizhenriqque18.ws_catalog.controller.dto;

import java.util.List;

public record ApiResponse<T>(
    List<T> data,
    PaginationResponse pagination
) { }