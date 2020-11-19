package com.example.topmovies.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.topmovies.entidades.Filme;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="favorite.db";
    private static final int DATABASE_VERSION=1;
    public static final String LOGTAG="FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public  FavoriteDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        db=dbhandler.getWritableDatabase();
    }
    public void close(){
        Log.i(LOGTAG,"Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase SQLitedb) {

        final String SQL_CREATE_FAVORITE_TABLE= "CREATE TABLE " + FavoriteContrat.FavoriteEntry.TABLE_NAME + " (" +
                FavoriteContrat.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoriteContrat.FavoriteEntry.COLUMN_ID + " INTEGER, " +
                FavoriteContrat.FavoriteEntry.COLUMN_TITULO + " TEXT NOT NULL, " +
                FavoriteContrat.FavoriteEntry.COLUMN_VOTO + " TEXT , " +
                FavoriteContrat.FavoriteEntry.COLUMN_POSTER + " TEXT, " +
                FavoriteContrat.FavoriteEntry.COLUMN_VGERAL + " TEXT )";

        SQLitedb.execSQL(SQL_CREATE_FAVORITE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteContrat.FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addFavorite(Filme filme){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(FavoriteContrat.FavoriteEntry.COLUMN_ID,filme.getId());
        values.put(FavoriteContrat.FavoriteEntry.COLUMN_TITULO,filme.getTitulo());
        values.put(FavoriteContrat.FavoriteEntry.COLUMN_VOTO,filme.getVoto());
        values.put(FavoriteContrat.FavoriteEntry.COLUMN_POSTER,filme.getCaminhoPoster());
        values.put(FavoriteContrat.FavoriteEntry.COLUMN_VGERAL,filme.getVisaogeral());

       long inseri = db.insert(FavoriteContrat.FavoriteEntry.TABLE_NAME,null,values);
       Log.d("Estou aqui 2", "inserio " + inseri);
        db.close();
    }

    public void deleteFavorite(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(FavoriteContrat.FavoriteEntry.TABLE_NAME, FavoriteContrat.FavoriteEntry.COLUMN_ID + "=" + id,null);

    }

    public List<Filme> getAllFavorite(){
        String[] colunas={
                FavoriteContrat.FavoriteEntry._ID,
                FavoriteContrat.FavoriteEntry.COLUMN_ID,
                FavoriteContrat.FavoriteEntry.COLUMN_TITULO,
                FavoriteContrat.FavoriteEntry.COLUMN_VOTO,
                FavoriteContrat.FavoriteEntry.COLUMN_POSTER,
                FavoriteContrat.FavoriteEntry.COLUMN_VGERAL
        };
        String sortOrder=
                FavoriteContrat.FavoriteEntry._ID + " ASC";
        List<Filme> favoritelist= new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(FavoriteContrat.FavoriteEntry.TABLE_NAME,
                colunas,
                null,
                null,
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst()){
            do{
                Filme filme= new Filme();
                filme.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoriteContrat.FavoriteEntry.COLUMN_ID))));
                filme.setTitulo(cursor.getString(cursor.getColumnIndex(FavoriteContrat.FavoriteEntry.COLUMN_TITULO)));
                filme.setVoto(cursor.getString(cursor.getColumnIndex(FavoriteContrat.FavoriteEntry.COLUMN_VOTO)));
                filme.setCaminhoPoster(cursor.getString(cursor.getColumnIndex(FavoriteContrat.FavoriteEntry.COLUMN_POSTER)));
                filme.setVisaogeral(cursor.getString(cursor.getColumnIndex(FavoriteContrat.FavoriteEntry.COLUMN_VGERAL)));

                favoritelist.add(filme);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoritelist;
    }
}
