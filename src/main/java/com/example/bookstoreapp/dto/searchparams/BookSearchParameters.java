package com.example.bookstoreapp.dto.searchparams;

public record BookSearchParameters(String[] titles, String[] authors, String[] isbns,
                                   String[] minPrices, String[] maxPrices,
                                   String[] descriptions) {
}
