package fpoly.edu.grocerymanager.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.grocerymanager.database.DbHelper;
import fpoly.edu.grocerymanager.model.Hang;

public class HangDAO {
    private SQLiteDatabase db;

    public HangDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Hang obj){
        ContentValues values = new ContentValues();
        values.put("tenHang",obj.getTenHang());
        values.put("gia",obj.getGia());
        values.put("maLoai", obj.getMaLoai());

        return db.insert("Hang",null, values);
    }
    public int update(Hang obj){
        ContentValues values = new ContentValues();
        values.put("tenHang",obj.getTenHang());
        values.put("gia",obj.getGia());
        values.put("maLoai",obj.getMaLoai());

        return db.update("Hang",values,"maHang=?",new String[]{String.valueOf(obj.getMaHang())});

    }
    public int delete(String id){
        return db.delete("Hang","maHang=?", new String[]{id});
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    public List<Hang> getData(String sql, String...selectionArgs){
        List<Hang> list = new ArrayList<Hang>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Hang obj = new Hang();
           obj.setMaHang(Integer.valueOf(c.getString(c.getColumnIndex("maHang"))));
           obj.setGia(Integer.parseInt(c.getString(c.getColumnIndex("gia"))));
           obj.setTenHang(c.getString(c.getColumnIndex("tenHang")));
           obj.setMaLoai(Integer.valueOf(c.getString(c.getColumnIndex("maLoai"))));

            list.add(obj);
        }
        return list;
    }
    // get tất cả data
    public List<Hang> getAll(){
        String sql ="SELECT * FROM Hang";
        return getData(sql);
    }
    //get data theo id
    public Hang getID(String id){
        String sql = "SELECT * FROM Hang WHERE maHang=?";
        List<Hang> list = getData(sql,id);
        return list.get(0);
    }
    //get name
    public Hang getName(String name){
        String sql = "SELECT * FROM Hang WHERE tenHang=?";
        List<Hang> list = getData(sql, name);
        return list.get(0);
    }
}
