package com.gameup.ticket_service.client;

import com.gameup.ticket_service.dto.AdminResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "admin-service", url = "${admin.service.url}")
public interface AdminFeignClient {

    @GetMapping("/api/admins/{id}")
    AdminResponseDTO obtenerAdminPorId(@PathVariable("id") Long id);
}