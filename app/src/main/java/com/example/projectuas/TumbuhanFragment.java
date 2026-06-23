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

public class TumbuhanFragment extends Fragment {

    private EndemikAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tumbuhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvTumbuhan = view.findViewById(R.id.rv_tumbuhan);
        rvTumbuhan.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new EndemikAdapter();
        rvTumbuhan.setAdapter(adapter);

        adapter.setOnItemClickListener(endemik -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("ENDEMIK_ID", endemik.id);
            intent.putExtra("NAME", endemik.name);
            intent.putExtra("DESC", endemik.description);
            intent.putExtra("IMG", endemik.imageRes);
            startActivity(intent);
        });

        AppDatabase.getInstance(getContext()).endemikDao().getEndemikByCategory("Tumbuhan").observe(getViewLifecycleOwner(), list -> {
            adapter.setList(list);
        });
    }
}
