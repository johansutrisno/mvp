package net.flyhighlabs.mvp.main;

import net.flyhighlabs.mvp.base.BaseView;
import net.flyhighlabs.mvp.model.Result;

public interface MainView extends BaseView {

    void success(Result data);
    void error();
}
