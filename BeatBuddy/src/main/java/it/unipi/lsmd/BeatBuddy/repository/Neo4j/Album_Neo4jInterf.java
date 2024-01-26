package it.unipi.lsmd.BeatBuddy.repository.Neo4j;

import it.unipi.lsmd.BeatBuddy.model.Album_Neo4j;
import it.unipi.lsmd.BeatBuddy.model.dummy.AlbumWithLikes;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface Album_Neo4jInterf extends Neo4jRepository<Album_Neo4j, String> {

    @Query("MATCH (a:Album) WHERE a.albumName =~ $albumName RETURN a")
    Album_Neo4j getAlbumByName(String albumName);

//    @Query("MATCH (a:Album) <-[r:LIKES_A]- (:User) " +
//            "WHERE date(r.timestamp) >= date() - duration('P7D') " +
//            "WITH a, count(r) as likes " +
//            "RETURN DISTINCT a.albumName as albumName, a.artistName as artistName, a.coverURL as coverURL, likes " +
//            "ORDER BY likes DESC " +
//            "LIMIT (5)")
//    AlbumWithLikes[] findAlbumsSortedByLikes_LastWeek();

//    @Query("MATCH (a:Album) <-[l:LIKES_A]- (:User) " +
////            "WHERE date(l.timestamp) >= date() - duration({days: 1}) " +
////            "WITH a " +
////            "MATCH (a) <-[r:LIKES_A]- (:User) " +
//            "RETURN DISTINCT a.coverURL as coverURL, count(l) as likes")
//    AlbumLikes[] getNewLikesForAlbums();

//    @Query("MATCH (s:Song) <-[l:LIKES_S]- (:User) " +
////            "WHERE date(l.timestamp) >= date() - duration({days: 1}) " +
//            "WITH s, count(l) as likes " +
//            "RETURN DISTINCT s.coverUrl as coverUrl, s.songName as songName, likes")
//    SongLikes[] getNewLikesForSongs();
}
