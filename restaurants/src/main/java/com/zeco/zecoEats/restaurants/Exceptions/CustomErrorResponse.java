package com.zeco.zecoEats.restaurants.Exceptions;

import lombok.Builder;

@Builder
public record CustomErrorResponse(int status, String message) {
}
