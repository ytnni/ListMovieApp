package com.yacine.listmovieapp.utils.genre

import com.yacine.listmovieapp.model.common.Genre



object GenreGenerator {
    fun getAllGenre(genres : List<Genre>) : String{
        var strGenres = mutableListOf<String>()
        for(genre in genres){
            strGenres.add(genre.name)
        }
        return strGenres.joinToString()
    }
}