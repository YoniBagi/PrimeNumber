package bagi.views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

public class CustomLtrGridLayoutManager extends GridLayoutManager {
    public CustomLtrGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomLtrGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CustomLtrGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    protected boolean isLayoutRTL() {
        return false;
    }
}
