package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CategoryTestSamples.*;
import static com.mycompany.myapp.domain.CommentTestSamples.*;
import static com.mycompany.myapp.domain.PlaylistTestSamples.*;
import static com.mycompany.myapp.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VideoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Video.class);
        Video video1 = getVideoSample1();
        Video video2 = new Video();
        assertThat(video1).isNotEqualTo(video2);

        video2.setId(video1.getId());
        assertThat(video1).isEqualTo(video2);

        video2 = getVideoSample2();
        assertThat(video1).isNotEqualTo(video2);
    }

    @Test
    void commentsTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Comment commentBack = getCommentRandomSampleGenerator();

        video.addComments(commentBack);
        assertThat(video.getComments()).containsOnly(commentBack);
        assertThat(commentBack.getVideo()).isEqualTo(video);

        video.removeComments(commentBack);
        assertThat(video.getComments()).doesNotContain(commentBack);
        assertThat(commentBack.getVideo()).isNull();

        video.comments(new HashSet<>(Set.of(commentBack)));
        assertThat(video.getComments()).containsOnly(commentBack);
        assertThat(commentBack.getVideo()).isEqualTo(video);

        video.setComments(new HashSet<>());
        assertThat(video.getComments()).doesNotContain(commentBack);
        assertThat(commentBack.getVideo()).isNull();
    }

    @Test
    void categoriesTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Category categoryBack = getCategoryRandomSampleGenerator();

        video.addCategories(categoryBack);
        assertThat(video.getCategories()).containsOnly(categoryBack);
        assertThat(categoryBack.getVideos()).containsOnly(video);

        video.removeCategories(categoryBack);
        assertThat(video.getCategories()).doesNotContain(categoryBack);
        assertThat(categoryBack.getVideos()).doesNotContain(video);

        video.categories(new HashSet<>(Set.of(categoryBack)));
        assertThat(video.getCategories()).containsOnly(categoryBack);
        assertThat(categoryBack.getVideos()).containsOnly(video);

        video.setCategories(new HashSet<>());
        assertThat(video.getCategories()).doesNotContain(categoryBack);
        assertThat(categoryBack.getVideos()).doesNotContain(video);
    }

    @Test
    void playlistTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Playlist playlistBack = getPlaylistRandomSampleGenerator();

        video.addPlaylist(playlistBack);
        assertThat(video.getPlaylists()).containsOnly(playlistBack);
        assertThat(playlistBack.getVideos()).containsOnly(video);

        video.removePlaylist(playlistBack);
        assertThat(video.getPlaylists()).doesNotContain(playlistBack);
        assertThat(playlistBack.getVideos()).doesNotContain(video);

        video.playlists(new HashSet<>(Set.of(playlistBack)));
        assertThat(video.getPlaylists()).containsOnly(playlistBack);
        assertThat(playlistBack.getVideos()).containsOnly(video);

        video.setPlaylists(new HashSet<>());
        assertThat(video.getPlaylists()).doesNotContain(playlistBack);
        assertThat(playlistBack.getVideos()).doesNotContain(video);
    }
}
