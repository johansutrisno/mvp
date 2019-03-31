package net.flyhighlabs.mvp.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.flyhighlabs.mvp.R;
import net.flyhighlabs.mvp.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    // sebagai key untuk menjaga state
    public static final String STATE = "STATE";

    @BindView(R.id.inputan_a)
    EditText inputanA;
    @BindView(R.id.inputan_b)
    EditText inputanB;
    @BindView(R.id.btn_calculate)
    Button btnCalculate;
    @BindView(R.id.tv_result)
    TextView tvResult;

    // deklarasi presenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // inisialisasi
        initPresenter();

        /**
         * untuk menjaga datanya agar tidak menghilang seperti dia
         */
        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE);
            tvResult.setText(result);
        }
    }

    private void initPresenter() {
        presenter = new MainPresenter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE, tvResult.getText().toString().trim());
    }

    @OnClick(R.id.btn_calculate)
    public void onViewClicked() {
        // getText digunakan untuk mengambil inputan dari editText
        // toString digunakan untuk memparsing data kedalam string
        // trim digunakan untuk menghapus space
        String penampungA = inputanA.getText().toString().trim();
        String penampungB = inputanB.getText().toString().trim();
        presenter.calculate(penampungA, penampungB);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDettachView() {
        presenter.onDettach();
    }

    @Override
    protected void onStart() {
        onAttachView();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        onDettachView();
        super.onDestroy();
    }

    @Override
    public void success(Result data) {
        tvResult.setText(data.getResult());
    }

    @Override
    public void error() {
        Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_SHORT).show();
    }
}
