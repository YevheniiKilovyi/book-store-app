package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.BookSearchParameters;
import com.example.bookstoreapp.dto.request.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.BookDto;
import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.mapper.BookMapper;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.book.BookRepository;
import com.example.bookstoreapp.repository.book.BookSpecificationBuilder;
import com.example.bookstoreapp.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get book by id: " + id)));
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't update book by id: " + id));
        existingBook.setTitle(requestDto.getTitle());
        existingBook.setAuthor(requestDto.getAuthor());
        existingBook.setIsbn(requestDto.getIsbn());
        existingBook.setPrice(requestDto.getPrice());
        existingBook.setDescription(requestDto.getDescription());
        existingBook.setCoverImage(requestDto.getCoverImage());
        return bookMapper.toDto(bookRepository.save(existingBook));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParameters params) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
