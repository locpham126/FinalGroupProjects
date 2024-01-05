package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Playlist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class PlaylistRepositoryWithBagRelationshipsImpl implements PlaylistRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Playlist> fetchBagRelationships(Optional<Playlist> playlist) {
        return playlist.map(this::fetchVideos);
    }

    @Override
    public Page<Playlist> fetchBagRelationships(Page<Playlist> playlists) {
        return new PageImpl<>(fetchBagRelationships(playlists.getContent()), playlists.getPageable(), playlists.getTotalElements());
    }

    @Override
    public List<Playlist> fetchBagRelationships(List<Playlist> playlists) {
        return Optional.of(playlists).map(this::fetchVideos).orElse(Collections.emptyList());
    }

    Playlist fetchVideos(Playlist result) {
        return entityManager
            .createQuery("select playlist from Playlist playlist left join fetch playlist.videos where playlist.id = :id", Playlist.class)
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<Playlist> fetchVideos(List<Playlist> playlists) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, playlists.size()).forEach(index -> order.put(playlists.get(index).getId(), index));
        List<Playlist> result = entityManager
            .createQuery(
                "select playlist from Playlist playlist left join fetch playlist.videos where playlist in :playlists",
                Playlist.class
            )
            .setParameter("playlists", playlists)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
