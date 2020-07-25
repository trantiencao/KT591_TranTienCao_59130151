package vn.edu.ntu.trantiencao.kt591_trantiencao_59130151;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FirstFragment extends Fragment {

    EditText inputDate, inputBuy, inputSell;
    ImageButton imageButtonDate;
    Button btnThem;
    Spinner spinnerLoai;
    String [] listLoai;
    ArrayAdapter adapterLoai;
    public static String loai;

    public static List<String> dsNgoaiTe = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        event();
        return view;
    }

    private void initView(View view) {
        inputDate = view.findViewById(R.id.editTextDate);
        inputDate.setEnabled(false);
        inputBuy = view.findViewById(R.id.editTextBuy);
        inputSell = view.findViewById(R.id.editTextSell);
        imageButtonDate = view.findViewById(R.id.imageButtonDate);
        btnThem = view.findViewById(R.id.buttonThem);
        spinnerLoai = view.findViewById(R.id.spinnerLoai);
        listLoai = getResources().getStringArray(R.array.listNgoaiTe);
        adapterLoai = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listLoai);
        spinnerLoai.setAdapter(adapterLoai);
    }

    private void event() {
        imageButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNgay();
            }
        });

        spinnerLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // position là phần tử được chọn
                loai = listLoai[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputDate.getText().toString().length() > 0
                        && inputBuy.getText().toString().length() > 0
                        && inputSell.getText().toString().length() > 0) {
                    String a = inputDate.getText().toString() + "\n" + loai + "\n"
                            + "Giá mua: " + inputBuy.getText().toString() + "\t"
                            + "Giá bán: " + inputSell.getText().toString() + "\n\n";
                    dsNgoaiTe.add(a);   // Khai báo toàn phần   public static List<String> dsNgoaiTe = new ArrayList<>();
                    Toast.makeText(getActivity(), "Đã thêm ngoại tệ", Toast.LENGTH_SHORT).show();
                    inputDate.setText("");
                    inputBuy.setText("");
                    inputSell.setText("");
                } else {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ cho các miền trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    private void setNgay() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String setDate = dayOfMonth + "/" + month + "/" + year;
                inputDate.setText(setDate);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), setListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}