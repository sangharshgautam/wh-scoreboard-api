package com.sangharsh.oms.repository;

import com.sangharsh.oms.exception.ResourceNotFoundException;
import com.sangharsh.oms.model.BaseModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, String> {
    @CacheEvict(allEntries = true)
    <S extends T> List<S> saveAll(Iterable<S> entities);

//    @PreAuthorize("#entity.id==null || #entity.createdBy == authentication.principal.user.id")
    <S extends T> S save(S entity);

//    Optional<T> findById(String id);

    @Cacheable
    default T getById(String id) {
        Optional<T> optionalModel = findById(id);
        if (optionalModel.isEmpty()) {
            throw new ResourceNotFoundException("Entity", "id", id);
        }
        return optionalModel.get();
    }
}
