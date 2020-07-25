package vn.edu.ntu.trantiencao.kt591_trantiencao_59130151;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    RecyclerView recyclerView;
    Adapter adapter;
    List<String> dsNgoaiTe = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        FragmentActivity activity = getActivity();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(activity,
                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        dsNgoaiTe = FirstFragment.dsNgoaiTe;
        adapter = new Adapter(dsNgoaiTe);
        recyclerView.setAdapter(adapter);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewShow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewShow = this.itemView.findViewById(R.id.textViewShow);
        }

        private void bind(String show) {
            textViewShow.setText(show);
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {
        List<String> dsNgoaiTe;
        public Adapter(List<String> dsNgoaiTe) {
            this.dsNgoaiTe = dsNgoaiTe;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//            LayoutInflater inflater = getLayoutInflater();
            View itemView = inflater.inflate(R.layout.show_ngoai_te, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(dsNgoaiTe.get(position));
        }

        @Override
        public int getItemCount() {
            return dsNgoaiTe.size();
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
//        });
    }
}