package com.example.KT_Giua_Ky.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tên là bắt buộc")
    @Size(min = 3, max = 50, message = "Tên phải có độ dài từ 3 đến 50 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Tên chỉ được chứa các ký tự chữ và số")
    private String name;
    @PositiveOrZero(message = "Giá phải là số dương hoặc bằng 0")
    private double price;
    private String description;
}