package com.oaso.pro.dictador;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    public static final String BRANDS = "fa-brands-400.ttf";
    public static final String REGULAR = "fa-regular-400.ttf";
    public static final String SOLID = "fa-solid-900.ttf";

    public static Typeface getTypeface(Context context, String font){
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
