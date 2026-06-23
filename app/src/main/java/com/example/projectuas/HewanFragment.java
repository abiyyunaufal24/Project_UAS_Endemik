package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuas.database.AppDatabase;
import com.example.projectuas.database.Endemik;

import java.util.List;

public class HewanFragment extends Fragment {

    private RecyclerView rvHewan;
    private EndemikAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hewan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvHewan = view.findViewById(R.id.rv_hewan);
        rvHewan.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new EndemikAdapter();
        rvHewan.setAdapter(adapter);

        adapter.setOnItemClickListener(endemik -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("ENDEMIK_ID", endemik.id);
            intent.putExtra("NAME", endemik.name);
            intent.putExtra("DESC", endemik.description);
            intent.putExtra("IMG", endemik.imageRes);
            startActivity(intent);
        });

        AppDatabase.getInstance(getContext()).endemikDao().getEndemikByCategory("Hewan").observe(getViewLifecycleOwner(), list -> {
            adapter.setList(list);
        });
    }
}
