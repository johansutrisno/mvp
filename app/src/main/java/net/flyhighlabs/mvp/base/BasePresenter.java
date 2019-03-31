package net.flyhighlabs.mvp.base;

public interface BasePresenter <T extends BaseView> {

    void onAttach(T view);
    void onDettach();

}