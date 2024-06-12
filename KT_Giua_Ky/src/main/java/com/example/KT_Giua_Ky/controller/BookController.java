package com.example.KT_Giua_Ky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {
    @GetMapping("/book")
    public String showBookList(Model model) {
        return "books-list";
    }
    @GetMapping("/book/add")
    public String showAddForm(Model model) {
//        model.addAttribute("product", new Product());
        return "add-book";
    }
    @GetMapping("/book/edit/{id}")
    public String showEditForm(Model model) {
        return "update-book";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
//        productService.deleteProductById(id);
        return "redirect:/book";
    }
}
