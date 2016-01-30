package ronniej.com.YBApp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuitPopupDialogFragment extends DialogFragment
{
    private static QuitPopupDialogFragment instance;
    public static QuitPopupDialogFragment getInstance()
    {
        if (instance == null)
            instance = new QuitPopupDialogFragment();

        return instance;
    }

    private View view;
    private Dialog dialog;
    private MainActivity refMainActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.close_dialog, null);
        ViewGroup viewGroup = (ViewGroup)view;

        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        refMainActivity = MainActivity.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(refMainActivity);
        builder.setView(view)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        refMainActivity.finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

        dialog = builder.create();

        return dialog;
    }
}