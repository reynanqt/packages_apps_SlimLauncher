package com.slim.slimlauncher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

public class CreateShortcut extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        final String[] dialogValues =
                getResources().getStringArray(R.array.shortcut_action_launcher_values);
        final String[] dialogEntries =
                getResources().getStringArray(R.array.shortcut_action_launcher_entries);

        AlertDialog.Builder action = new AlertDialog.Builder(this);
        action.setTitle(R.string.shortcut_picker_title)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CreateShortcut.this.finish();
                    }
                })
                .setItems(dialogEntries, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent shortcutIntent = new Intent(
                                CreateShortcut.this, Launcher.class);
                        shortcutIntent.setAction(Intent.ACTION_VIEW);
                        shortcutIntent.putExtra(ShortcutHelper.SHORTCUT_VALUE, dialogValues[i]);
                        shortcutIntent.putExtra(ShortcutHelper.SLIM_LAUNCHER_SHORTCUT, true);

                        Bitmap icon = ShortcutHelper.getIcon(
                                CreateShortcut.this, dialogValues[i]);

                        Intent intent = new Intent();
                        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, icon);
                        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, dialogEntries[i]);
                        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                        setResult(RESULT_OK, intent);
                        CreateShortcut.this.finish();
                    }
                });
        action.show();
    }
}
