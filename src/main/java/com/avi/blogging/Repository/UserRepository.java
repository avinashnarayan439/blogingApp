package com.avi.blogging.Repository;

import com.avi.blogging.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
