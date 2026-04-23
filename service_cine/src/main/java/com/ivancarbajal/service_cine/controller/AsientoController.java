    package com.ivancarbajal.service_cine.controller;

    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.ivancarbajal.service_cine.dto.AsientoRegistroDTO;
    import com.ivancarbajal.service_cine.service.AsientoService;

    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;

    @RestController
    @RequestMapping("/api/asiento")
    @RequiredArgsConstructor
    public class AsientoController {

        private final AsientoService asientoService;

        @PostMapping("/registro")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<String> registrarAsiento(@Valid @RequestBody AsientoRegistroDTO dto) {
            try {
                asientoService.registrarAsiento(dto);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Asiento registrado con exito");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
            }
        }

    }
