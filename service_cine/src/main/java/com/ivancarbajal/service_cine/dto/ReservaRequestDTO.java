package com.ivancarbajal.service_cine.dto;

import java.util.List;

public record ReservaRequestDTO(
                Long usuarioId,
                Long funcionId,
                List<Long> asientosIds) {
}