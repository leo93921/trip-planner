package it.unisalento.tripplanner.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton @Component
public class PageBuilder<T> {

    private List<T> elements = new ArrayList<>();
    private Pageable pageRequest;
    private long totalElements;

    public PageBuilder() {
    }

    public PageBuilder<T> setElements(List<T> elements) {
        this.elements = elements;
        return this;
    }

    public PageBuilder<T> setPage(Pageable page) {
        this.pageRequest = page;
        return this;
    }

    public PageBuilder<T> setTotalElements(long numberOfElements) {
        this.totalElements = numberOfElements;
        return this;
    }

    public Page<T> build() {
        return new PageImpl<>(this.elements, this.pageRequest, this.totalElements);
    }
}
