package com.gmail.pavlovsv93.notepadv2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pavlovsv93.notepadv2.domain.Note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> noteList = new ArrayList<>();

    private OnClick onClick;

    public void setNoteList(Collection<Note> notes) {
        noteList.clear();
        noteList.addAll(notes);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_task, parent, false);
        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = noteList.get(position);

        holder.getTitle().setText(note.title);
        holder.getData().setText(note.timestemp);

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public OnClick getOnClick() {
        return onClick;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick {

        void onClick(Note note);

        void onClickEdit(Note note);

        void onClickDone(Note note);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView data;
        private Button edit;
        private Button done;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.text_title);
            data = itemView.findViewById(R.id.text_date);
            edit = itemView.findViewById(R.id.btn_edit);
            done = itemView.findViewById(R.id.btn_done);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = noteList.get(getAdapterPosition());
                    if (getOnClick() != null){
                        getOnClick().onClickEdit(note);
                    }
                }
            });

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = noteList.get(getAdapterPosition());
                    if (getOnClick() != null){
                        getOnClick().onClickDone(note);
                    }
                }
            });

            itemView.findViewById(R.id.card_curr).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = noteList.get(getAdapterPosition());
                    if (getOnClick() != null) {
                        getOnClick().onClick(note);
                    }
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getData() {
            return data;
        }

        public Button getEdit() {
            return edit;
        }

        public Button getDone() {
            return done;
        }
    }

}
