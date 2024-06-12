package com.example.KT_Giua_Ky.service;

import com.example.KT_Giua_Ky.model.Book;


import com.example.KT_Giua_Ky.respository.BookRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository productRepository;
    // Retrieve all products from the database
    public List<Book> getAllProducts() {
        return productRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Book> getProductById(Long id) {
        return productRepository.findById(id);
    }
    // Add a new product to the database
    public Book addProduct(Book book) {
        return productRepository.save(book);
    }
    // Update an existing product
    public Book updateProduct(@NotNull Book book) {
        Book existingBook = productRepository.findById(book.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        book.getId() + " does not exist."));
        existingBook.setName(book.getName());
        existingBook.setPrice(book.getPrice());
        existingBook.setDescription(book.getDescription());
        return productRepository.save(existingBook);
    }
    // Delete a product by its id
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }
}
