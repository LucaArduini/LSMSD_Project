package it.unipi.lsmd.BeatBuddy.repository;

import it.unipi.lsmd.BeatBuddy.DTO.AlbumDTO;
import it.unipi.lsmd.BeatBuddy.DTO.SongDTO;
import it.unipi.lsmd.BeatBuddy.model.Album;
import it.unipi.lsmd.BeatBuddy.repository.MongoDB.Album_MongoInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class Album_Repo {

    @Autowired
    private Album_MongoInterf album_RI;

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean existsById(String id){
        try {
            return album_RI.existsById(id);
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return false;
        }
    }

    public Optional<Album> getAlbumById(String id){
        try {
            return album_RI.findById(id);
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Album> getAlbumsByTitleAndArtist(String title, String artist){
        try {
            return album_RI.findAlbumsByTitleAndArtist(title, artist);
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return null;
        }
    }

//    public Optional<Album> getAlbumIdByTitleAndArtists(String title, List<String> artists) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("title").is(title).and("artists").all(artists));
//        query.fields().include("id");
//
//        try {
//            Album album = mongoTemplate.findOne(query, Album.class);
//            return album != null ? album_RI.findById(album.getId()) : Optional.empty();
//        } catch (DataAccessException dae) {
//            dae.printStackTrace();
//            return Optional.empty();
//        }
//    }

    public List<AlbumDTO> find5AlbumDTO(String term){
        Pageable topFive = PageRequest.of(0, 5);
        return album_RI.findFirst5ByTitleContaining(term, topFive);
    }

    public List<SongDTO> find5SongDTO(String term) {
        Query query = new Query();
        query.addCriteria(Criteria.where("songs.name").regex(term, "i"));
        query.limit(5); // Questo limita il numero di album, non di canzoni

        List<Album> albums = mongoTemplate.find(query, Album.class);

        return albums.stream()
                .flatMap(album -> Arrays.stream(album.getSongs())
                        .filter(song -> song.getName().toLowerCase().contains(term.toLowerCase()))
                        .map(song -> new SongDTO(
                                song.getName(),
                                album.getTitle(),
                                album.getId(),
                                album.getCoverURL(),
                                album.getArtists()
                        )))
                .limit(5) // Limita effettivamente i risultati delle canzoni a 5
                .collect(Collectors.toList());
    }

//    public int insertReviewIntoAlbum(String albumID, String username, int rating, String text){
//        Album targetAlbum = getAlbumById(albumID).orElse(null);
//        if (targetAlbum == null) {
//            return 1; // Album non trovato
//        }
//
//        ReviewLite tmp_reviewLite = new ReviewLite(username, rating);
//
//
//
//
//        System.out.println("Review inserita con successo in username: " + username);
//    }
}