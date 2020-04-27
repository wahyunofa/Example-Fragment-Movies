package com.nopex77.movies.film;

import com.nopex77.movies.R;

import java.util.ArrayList;

public class FilmData_class {
    private static String[] filmNames = {
            "The Walking Dead Season 1",
            "The Walking Dead Season 2",
            "The Walking Dead Season 3",
            "The Walking Dead Season 4",
            "The Walking Dead Season 5",
            "The Walking Dead Season 6",
            "The Walking Dead Season 7",
            "The Walking Dead Season 8",
            "The Walking Dead Season 9",
            "The Walking Dead Season 10"
    };

    private static String[] filmDetails = {
            "Episode 1 sampai 6",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16",
            "Episode 1 sampai 16"
    };

    private static int[] filmImages = {
            R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3,
            R.drawable.s4,
            R.drawable.s5,
            R.drawable.s6,
            R.drawable.s7,
            R.drawable.s8,
            R.drawable.s9,
            R.drawable.s10
    };

    public static ArrayList<Film_class> getListData() {
        ArrayList<Film_class> list = new ArrayList<>();
        for (int position = 0; position < filmNames.length; position++) {
            Film_class film = new Film_class();
            film.setNama(filmNames[position]);
            film.setRincian(filmDetails[position]);
            film.setFoto(filmImages[position]);
            list.add(film);
        }
        return list;
    }
}
