package com.sangharsh.oms.service;

import com.sangharsh.oms.dto.Greeting;
import com.sangharsh.oms.dto.ResultDTO;
import com.sangharsh.oms.exception.ResourceNotFoundException;
import com.sangharsh.oms.mapper.GenericMapper;
import com.sangharsh.oms.model.BaseModel;
import com.sangharsh.oms.model.User;
import com.sangharsh.oms.repository.BaseRepository;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<K extends BaseRepository<T>, T extends BaseModel, U> {

    @Autowired
    protected AuditorAware<String> auditorProvider;

    @Autowired
    protected UserService userService;

    @Autowired
    protected SimpMessagingTemplate messagingTemplate;

    protected final K repository;
    protected final GenericMapper<T, U> mapper;
    GenericService(K repository, GenericMapper<T, U> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public Optional<U> findById(String id) {
        final T object = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(getModelName(), "id", id));
        if (object == null) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(object));
    }

    public T create(T object) {

        return this.repository.save(object);
    }
    public void delete(String id) {
        final T object = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(getModelName(), "id", id));
        object.setDeleted(true);
        this.repository.save(object);
    }
    public T update(String id, U dto) {
        final T object = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(getModelName(), "id", id));
        if (object == null) {
            return null;
        }
        T updated = mapper.fromTwoOther(dto, object);
        this.repository.save(updated);
        messagingTemplate.convertAndSend(String.format("/topic/%s",id), updated);
        return updated;

    }
    public List<U> findAll() {
        List<T> objects = this.repository.findAll();
        if (objects == null) {
            return null;
        }
        return mapper.map(objects);
    }
    public U toDto(T object) {
        return mapper.toDto(object);
    }

    private Class<?> getModelName() {
        String className = TypeUtils.getTypeArguments(getClass(), GenericService.class).get(GenericService.class.getTypeParameters()[1]).getTypeName();
        Class<?> clazz = null;
        try {
            clazz = ClassUtils.getClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public ResultDTO<U> findByLoggedInUser(final int page, final int size) {

        return new ResultDTO<U>();
    }
    protected ResultDTO<U> pageToResult(Page<T> page){
        List<U> dtos = mapper.map(page.getContent());
        ResultDTO<U> result = new ResultDTO<U>();
        result.setTotal(page.getTotalElements());
        result.setRecords(dtos);
        return result;
    }

    protected User getLoggedInUser() {
        String id = auditorProvider.getCurrentAuditor().get();
        return userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, "id", id));
    }
}
