package com.ish.qswallpaper.manager;

import android.support.v7.widget.GridLayoutManager;

/**
 * @author ish
 * @date 2018/5/27.
 */

public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private final GridLayoutManager layoutManager;

    public HeaderSpanSizeLookup(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        position = position == 0 ? layoutManager.getSpanCount() : 1;
        return position;
    }

}
