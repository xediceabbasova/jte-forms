package com.khadija.forms;

import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
}
