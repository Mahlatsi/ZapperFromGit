package com.example.monty.zapper.helper;

import com.example.monty.zapper.R;

/**
 * Created by Monty on 5/8/2017.
 */

public class Constants {
    public static final String ROOT_URL ="http://demo4012764.mockable.io";
    public static final String STATE_ITEMS = "items";

    public static int getBackgroundColor(int position) {
        int color;
        int num = position % 6;

        switch (num) {
            case 0:
                color = R.color.app_color_lime;
                break;
            case 1:
                color = R.color.app_color_orange;
                break;
            case 2:
                color = R.color.app_color_blue;
                break;
            case 3:
                color = R.color.app_color_red;
                break;
            case 4:
                color = R.color.app_color_purple;
                break;
            default:
                color = R.color.app_custom_color;
                break;
        }
        Position(position);
        return  color;
    }

    public static int Position(int posistion){
        return posistion;
    }
}
