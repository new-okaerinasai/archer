package new_okaerinasai.com.archer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomColorChoosingDialog extends AppCompatActivity {
    private int color;

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

    @BindView(R.id.chooseButton)
    Button chooseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_color);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        picker.addSVBar(svBar);
        picker.addOpacityBar(opacityBar);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);

        setListeners();
    }

    private void setListeners() {
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = picker.getColor();
                Intent data = new Intent();
                data.putExtra("chosenColor", color);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
