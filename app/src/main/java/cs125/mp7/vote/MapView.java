package cs125.mp7.vote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMapOptions;

public class MapView extends FrameLayout {

    public MapView(@NonNull Context context) {
        super(context);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MapView(@NonNull Context context, GoogleMapOptions options) {
    }
}