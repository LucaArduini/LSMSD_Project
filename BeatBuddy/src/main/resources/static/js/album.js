$(document).ready(function (){
    $(".song-container").click(function (){
        let name = $("#artist_name").text();
        let album = $(this).find("#album_title").text();
        album = encodeURIComponent(album);

        window.location.href = '/albumDetails?title=' + album + '&artist=' + name;
    });
});