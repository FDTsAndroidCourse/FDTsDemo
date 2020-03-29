package onl.fdt.android.fdtsdemo.InputListener;

import android.view.View;

import java.util.logging.Logger;

public class InputListener implements View.OnClickListener, View.OnLongClickListener, View.OnFocusChangeListener {
    public static final Logger LOGGER = Logger.getLogger(InputListener.class.getName());

    public InputListener(View view) {
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        view.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        LOGGER.info(v.getId() + " onClick");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        LOGGER.info(v.getId() + " onFocusChange");
    }

    @Override
    public boolean onLongClick(View v) {
        LOGGER.info(v.getId() + " onLongClick");
        return false;
    }
}
