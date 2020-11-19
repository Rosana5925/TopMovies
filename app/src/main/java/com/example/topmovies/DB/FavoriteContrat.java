package com.example.topmovies.DB;

import android.provider.BaseColumns;

public class FavoriteContrat {

    public  static final class FavoriteEntry implements BaseColumns{
       public static final String TABLE_NAME="favorite";
       public static final String COLUMN_ID="id";
        public static final String COLUMN_TITULO="titulo";
        public static final String COLUMN_VOTO="voto";
        public static final String COLUMN_POSTER="poster";
        public static final String COLUMN_VGERAL="visaogeral";
    }
}
