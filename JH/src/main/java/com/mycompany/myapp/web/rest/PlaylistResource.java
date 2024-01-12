package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Playlist;
import com.mycompany.myapp.repository.PlaylistRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Playlist}.
 */
@RestController
@RequestMapping("/api/playlists")
@Transactional
public class PlaylistResource {

    private final Logger log = LoggerFactory.getLogger(PlaylistResource.class);

    private static final String ENTITY_NAME = "playlist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlaylistRepository playlistRepository;

    public PlaylistResource(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    /**
     * {@code POST  /playlists} : Create a new playlist.
     *
     * @param playlist the playlist to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new playlist, or with status {@code 400 (Bad Request)} if the playlist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) throws URISyntaxException {
        log.debug("REST request to save Playlist : {}", playlist);
        if (playlist.getId() != null) {
            throw new BadRequestAlertException("A new playlist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Playlist result = playlistRepository.save(playlist);
        return ResponseEntity
            .created(new URI("/api/playlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /playlists/:id} : Updates an existing playlist.
     *
     * @param id the id of the playlist to save.
     * @param playlist the playlist to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playlist,
     * or with status {@code 400 (Bad Request)} if the playlist is not valid,
     * or with status {@code 500 (Internal Server Error)} if the playlist couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Playlist playlist
    ) throws URISyntaxException {
        log.debug("REST request to update Playlist : {}, {}", id, playlist);
        if (playlist.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, playlist.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!playlistRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Playlist result = playlistRepository.save(playlist);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, playlist.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /playlists/:id} : Partial updates given fields of an existing playlist, field will ignore if it is null
     *
     * @param id the id of the playlist to save.
     * @param playlist the playlist to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playlist,
     * or with status {@code 400 (Bad Request)} if the playlist is not valid,
     * or with status {@code 404 (Not Found)} if the playlist is not found,
     * or with status {@code 500 (Internal Server Error)} if the playlist couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Playlist> partialUpdatePlaylist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Playlist playlist
    ) throws URISyntaxException {
        log.debug("REST request to partial update Playlist partially : {}, {}", id, playlist);
        if (playlist.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, playlist.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!playlistRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Playlist> result = playlistRepository.findById(playlist.getId()).map(playlistRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, playlist.getId().toString())
        );
    }

    /**
     * {@code GET  /playlists} : get all the playlists.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of playlists in body.
     */
    @GetMapping("")
    public List<Playlist> getAllPlaylists(
        @RequestParam(name = "filter", required = false) String filter,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        if ("userprofile-is-null".equals(filter)) {
            log.debug("REST request to get all Playlists where userProfile is null");
            return StreamSupport
                .stream(playlistRepository.findAll().spliterator(), false)
                .filter(playlist -> playlist.getUserProfile() == null)
                .toList();
        }
        log.debug("REST request to get all Playlists");
        if (eagerload) {
            return playlistRepository.findAllWithEagerRelationships();
        } else {
            return playlistRepository.findAll();
        }
    }

    /**
     * {@code GET  /playlists/:id} : get the "id" playlist.
     *
     * @param id the id of the playlist to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the playlist, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable("id") Long id) {
        log.debug("REST request to get Playlist : {}", id);
        Optional<Playlist> playlist = playlistRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(playlist);
    }

    /**
     * {@code DELETE  /playlists/:id} : delete the "id" playlist.
     *
     * @param id the id of the playlist to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable("id") Long id) {
        log.debug("REST request to delete Playlist : {}", id);
        playlistRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
