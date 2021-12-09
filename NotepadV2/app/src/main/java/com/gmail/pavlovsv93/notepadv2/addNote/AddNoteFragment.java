package com.gmail.pavlovsv93.notepadv2.addNote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmail.pavlovsv93.notepadv2.R;

public class AddNoteFragment extends Fragment {

    public AddNoteFragment(){
        super(R.layout.fragment_add_note);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnSave = view.findViewById(R.id.btn_save_note);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "СОХРАНЕНО <<Не реализовано>>", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
