package com.example.KT_Giua_Ky.controller;

import com.example.KT_Giua_Ky.model.Book;
import com.example.KT_Giua_Ky.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public String showBookList(Model model) {
        model.addAttribute("books", bookService.getAllProducts());
        return "books-list";
    }
    @GetMapping("/book/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }
    @PostMapping("/book/add")
    public String addProduct(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/book/add";
        }
        bookService.addProduct(book);
        return "redirect:/book";
    }
    @GetMapping("/book/edit/{id}")
    public String showEditForm(@PathVariable Long id,Model model) {
        Book book = bookService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("book", book);
        return "update-book";
    }
    @PostMapping("/book/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Book book,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id); // set id to keep it in the form in case of errors
            return "update-book";
        }
        bookService.updateProduct(book);
        return "redirect:/book";
    }
    @GetMapping("/book/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        bookService.deleteProductById(id);
        return "redirect:/book";
    }
}
