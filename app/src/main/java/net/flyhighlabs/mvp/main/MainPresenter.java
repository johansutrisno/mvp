package net.flyhighlabs.mvp.main;

import android.widget.Toast;

import net.flyhighlabs.mvp.base.BasePresenter;
import net.flyhighlabs.mvp.model.Result;

public class MainPresenter implements BasePresenter<MainView> {

    // deklarasi class view
    private MainView mainView;

    @Override
    public void onAttach(MainView view) {
        mainView = view;
    }

    // create method / function untuk kalkulasi
    public void calculate(String inputanA, String inputanB) {

        if (inputanA.isEmpty() || inputanB.isEmpty()) {
            mainView.error();
        } else {

            // kalikan valueA dan valueB
            double result = Double.parseDouble(inputanA) * Double.parseDouble(inputanB);

            // create object dari sebuah class
            Result data = new Result(String.valueOf(result));

            mainView.success(data);
        }
    }

    @Override
    public void onDettach() {
        mainView = null;
    }
}
