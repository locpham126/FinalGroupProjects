package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.PlaylistTestSamples.*;
import static com.mycompany.myapp.domain.UserProfileTestSamples.*;
import static com.mycompany.myapp.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PlaylistTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Playlist.class);
        Playlist playlist1 = getPlaylistSample1();
        Playlist playlist2 = new Playlist();
        assertThat(playlist1).isNotEqualTo(playlist2);

        playlist2.setId(playlist1.getId());
        assertThat(playlist1).isEqualTo(playlist2);

        playlist2 = getPlaylistSample2();
        assertThat(playlist1).isNotEqualTo(playlist2);
    }

    @Test
    void videosTest() throws Exception {
        Playlist playlist = getPlaylistRandomSampleGenerator();
        Video videoBack = getVideoRandomSampleGenerator();

        playlist.addVideos(videoBack);
        assertThat(playlist.getVideos()).containsOnly(videoBack);

        playlist.removeVideos(videoBack);
        assertThat(playlist.getVideos()).doesNotContain(videoBack);

        playlist.videos(new HashSet<>(Set.of(videoBack)));
        assertThat(playlist.getVideos()).containsOnly(videoBack);

        playlist.setVideos(new HashSet<>());
        assertThat(playlist.getVideos()).doesNotContain(videoBack);
    }

    @Test
    void userProfileTest() throws Exception {
        Playlist playlist = getPlaylistRandomSampleGenerator();
        UserProfile userProfileBack = getUserProfileRandomSampleGenerator();

        playlist.setUserProfile(userProfileBack);
        assertThat(playlist.getUserProfile()).isEqualTo(userProfileBack);
        assertThat(userProfileBack.getPlaylist()).isEqualTo(playlist);

        playlist.userProfile(null);
        assertThat(playlist.getUserProfile()).isNull();
        assertThat(userProfileBack.getPlaylist()).isNull();
    }
}
