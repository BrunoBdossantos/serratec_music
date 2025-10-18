package com.serratec.music.dto;

import java.util.Set;
import jakarta.validation.constraints.NotNull;

public class PlaylistUpdateMusicasDTO {
    @NotNull
    private Set<Long> musicaIds; 

   
    public Set<Long> getMusicaIds() { return musicaIds; }
    public void setMusicaIds(Set<Long> musicaIds) { this.musicaIds = musicaIds; }
}