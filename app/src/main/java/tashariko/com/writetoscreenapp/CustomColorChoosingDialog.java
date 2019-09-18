package tashariko.com.writetoscreenapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

import butterknife.BindView;

public class CustomColorChoosingDialog extends AppCompatActivity {
    private ColorPicker colorPicker;

    @BindView(R.id.valuebar)
    ValueBar valueBar;

    @BindView(R.id.picker)
    ColorPicker picker;

    @BindView(R.id.svbar)
    SVBar svBar;

    @BindView(R.id.opacitybar)
    OpacityBar opacityBar;

    @BindView(R.id.saturationbar)
    SaturationBar saturationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        picker.addSVBar(svBar);
        picker.addOpacityBar(opacityBar);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);

        picker.getColor();
    }

    private void setListeners() {

    }
}
