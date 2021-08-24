package me.hjhng125.jpademo.repository;

import me.hjhng125.jpademo.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ItemJpaRepository<T extends Item> extends JpaRepository<T, Long> {
}
