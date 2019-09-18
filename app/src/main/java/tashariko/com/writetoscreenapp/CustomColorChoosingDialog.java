package tashariko.com.writetoscreenapp;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

import com.larswerkman.holocolorpicker.ColorPicker;

public class CustomColorChoosingDialog extends AppCompatActivity {
    private ColorPicker colorPicker;

    @Override
    protected void onCreate() {

    }

    private void init() {
        colorPicker = (ColorPicker) findViewById(R.id.colorPicker);
    }
}
