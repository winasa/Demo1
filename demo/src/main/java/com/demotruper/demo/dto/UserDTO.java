package com.demotruper.demo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String nombre;
    private Integer edad;
}
