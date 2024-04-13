package com.example.myrvjava;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder>
{
    private List<CharacterModel> characterList;
    private Context context;

    public CharacterAdapter(List<CharacterModel> characterList, Context context) {
        this.characterList = characterList;
        this.context = context;
    }
    public void setFilteredList(List<CharacterModel> filteredList){
       this.characterList= filteredList;notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final CharacterModel character = characterList.get(position);
        holder.characterImage.setImageResource(character.getImageResId());
        holder.characterName.setText(character.getName());
        holder.characterDescription.setText(character.getDescription());

        // Set click listener for each character item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCharacterPopup(character);
            }
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView characterImage;
        TextView characterName;
        TextView characterDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            characterImage = itemView.findViewById(R.id.characterImage);
            characterName = itemView.findViewById(R.id.characterName);
            characterDescription = itemView.findViewById(R.id.characterDescription);
        }
    }

    private void showCharacterPopup(CharacterModel character) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(character.getName())
                .setMessage(character.getDescription())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
    public void setFilter(List<CharacterModel> filteredList) {
        characterList = filteredList;
        notifyDataSetChanged();
    }
}