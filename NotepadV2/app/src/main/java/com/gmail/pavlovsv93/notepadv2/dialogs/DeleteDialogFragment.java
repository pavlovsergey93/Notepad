package com.gmail.pavlovsv93.notepadv2.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.domain.Note;

public class DeleteDialogFragment extends DialogFragment {

    public static final String KEY_DIALOG = "KEY_DIALOG";
    public static final String ARG_DIALOG = "ARG_DIALOG";
    private static final String ARG_TITLE = "ARG_TITLE";

    public static DeleteDialogFragment newInstans(Note note){
        DeleteDialogFragment ddf = new DeleteDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TITLE, note);
        ddf.setArguments(bundle);
        return ddf;
    }

    DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Bundle data_btn = new Bundle();
            data_btn.putInt(ARG_DIALOG, which);
            getParentFragmentManager().setFragmentResult(KEY_DIALOG, data_btn);
        }
    };


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Note note = requireArguments().getParcelable(ARG_TITLE);
        return new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.ic_baseline_delete_forever_24)
                .setTitle(note.getTitle())
                .setMessage(R.string.message_dialog)
                .setPositiveButton(R.string.positive_dialog, clickListener)
                .setNegativeButton(R.string.negative_dialog, clickListener)
                .setCancelable(false)
                .create();
    }
}
