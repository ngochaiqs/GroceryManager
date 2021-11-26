package fpoly.edu.grocerymanager.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.grocerymanager.R;
import fpoly.edu.grocerymanager.adapter.HangAdapter;
import fpoly.edu.grocerymanager.adapter.LoaiHangSpinnerAdapter;
import fpoly.edu.grocerymanager.dao.HangDAO;
import fpoly.edu.grocerymanager.dao.LoaiHangDAO;
import fpoly.edu.grocerymanager.model.Hang;
import fpoly.edu.grocerymanager.model.LoaiHang;

public class HangFragment extends Fragment {
    SearchView searchView;
    SearchManager searchManager;


    ListView lvHang;
    HangDAO hangDAO;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaHang, edTenHang, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;


    HangAdapter adapter;
    Hang item;
    List<Hang> list;

    LoaiHangSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiHang> listLoaiHang;
    LoaiHangDAO loaiHangDAO;
    LoaiHang loaiHang;
    int maLoaiHang, position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_hang, container, false);
        lvHang = v.findViewById(R.id.lvHang);
        registerForContextMenu(lvHang);

        searchView = v.findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        hangDAO = new HangDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                lvHang.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                lvHang.setAdapter(adapter);
                return false;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);

            }
        });
        lvHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });//nhấn và giữ
        return v;
    }



    void capNhatLv(){
        list = (List<Hang>) hangDAO.getAll();
        adapter = new HangAdapter(getActivity(),this, list);
        lvHang.setAdapter(adapter);
    }
    public void xoa(final String Id){
        //sư dụng alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn muốn xóa không");
        builder.setCancelable(true);

        builder.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //gọi function Delete
                        hangDAO.delete(Id);
                        Toast.makeText(getActivity(), "Đã xoá thành công", Toast.LENGTH_SHORT).show();
                        //cập nhât listview
                        capNhatLv();
                        dialog.cancel();

                    }
                });
        builder.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        builder.show();
    }
    protected void openDialog(final Context context,final int type){
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.hang_dialog);
        edMaHang = dialog.findViewById(R.id.edMaHang);
        edTenHang = dialog.findViewById(R.id.edTenHang);
        edGiaThue = dialog.findViewById(R.id.edGiaHang);
        spinner = dialog.findViewById(R.id.spLoaiHang);
        btnCancel = dialog.findViewById(R.id.btnCancelHang);
        btnSave = dialog.findViewById(R.id.btnSaveHang);

        listLoaiHang = new ArrayList<LoaiHang>();
        loaiHangDAO = new LoaiHangDAO(context);
        listLoaiHang = (ArrayList<LoaiHang>) loaiHangDAO.getAll();

        spinnerAdapter = new LoaiHangSpinnerAdapter(context,listLoaiHang);

        spinner.setAdapter(spinnerAdapter);
        // lay maloaihang
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiHang = Integer.parseInt(String.valueOf(listLoaiHang.get(position).getMaLoai()));
                Toast.makeText(context,"Chọn "+listLoaiHang.get(position).getTenLoai(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //kiểm tra type insert 0 hay update 1
        edMaHang.setEnabled(false);
        if (type != 0){
            edMaHang.setText(String.valueOf(item.getMaHang()));
            edTenHang.setText(item.getTenHang());
            edGiaThue.setText(String.valueOf(item.getGia()));
            for (int i = 0; i < listLoaiHang.size(); i++)
                if (item.getMaLoai() == (listLoaiHang.get(i).getMaLoai())){
                    position = i;
                }
            Log.i("demo","posHang "+position);
            spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Hang();
                item.setTenHang(edTenHang.getText().toString());
                item.setGia(Integer.parseInt(edGiaThue.getText().toString()));
                item.setMaLoai(Integer.valueOf(String.valueOf(maLoaiHang)));
                if (validate()>0){
                    if (type==0){
                        //type = 0 (insert)
                        if (hangDAO.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        //type = 1(update)
                        item.setMaHang(Integer.valueOf(edMaHang.getText().toString()));
                        if (hangDAO.update(item)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                        }

                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if (edTenHang.getText().length()==0 || edGiaThue.getText().length()==0) {
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    private void TimKiem(){
        list = (List<Hang>) hangDAO.getAll();
        adapter = new HangAdapter(getActivity(),this, list);
        lvHang.setAdapter(adapter);
    }

}