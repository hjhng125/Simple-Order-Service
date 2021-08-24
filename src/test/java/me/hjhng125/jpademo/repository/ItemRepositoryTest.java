package me.hjhng125.jpademo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.hjhng125.jpademo.domain.entity.Album;
import me.hjhng125.jpademo.domain.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void save() {

        itemRepository.save(Album.builder()
            .artist("Hong")
            .build());

        List<Album> result = entityManager.createQuery("select a from Album a", Album.class).getResultList();
        result.forEach(System.out::println);
        assertThat(result).extracting("artist").contains("Hong");
    }

    @Test
    void findById() {
        Album album = Album.builder()
            .artist("Hong")
            .build();
        itemRepository.save(album);

        Album byId = (Album) itemRepository.findById(album.getId());
        System.out.println("byId = " + byId);
        assertThat(byId.getArtist()).isEqualTo("Hong");
    }

    @Test
    void findAll() {
        itemRepository.save(Album.builder()
            .artist("Hong")
            .build());

        List<Item> all = itemRepository.findAll();
        all.forEach(System.out::println);
        assertThat(all).isNotEmpty();
    }

}