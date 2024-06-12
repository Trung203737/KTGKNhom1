package com.example.KT_Giua_Ky.respository;

import com.example.KT_Giua_Ky.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
