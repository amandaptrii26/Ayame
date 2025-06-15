package com.example.latihan_praktikum8_amandaputri.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihan_praktikum8_amandaputri.R;
import com.example.latihan_praktikum8_amandaputri.data.entity.Konten;

import java.util.List;

public class KontenAdapter extends RecyclerView.Adapter<KontenAdapter.KontenViewHolder> {

    private List<Konten> kontenList;

    public KontenAdapter(List<Konten> kontenList) {
        this.kontenList = kontenList;
    }

    @NonNull
    @Override
    public KontenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_konten, parent, false);
        return new KontenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontenViewHolder holder, int position) {
        Konten konten = kontenList.get(position);
        holder.titleTextView.setText(konten.getTitle());
    }

    @Override
    public int getItemCount() {
        return kontenList.size();
    }

    public void setData(List<Konten> newKontenList) {
        this.kontenList = newKontenList;
        notifyDataSetChanged();
    }

    static class KontenViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public KontenViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
