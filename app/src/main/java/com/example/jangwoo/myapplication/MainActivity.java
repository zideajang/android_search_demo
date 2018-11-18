package com.example.jangwoo.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.search_activity_search_editText)
    EditText editText;
    @BindView(R.id.search_activity_search_result_textView)
    TextView textView;

    @BindView(R.id.search_activity_goback_textView)
    TextView gobackTextView;
    @BindView(R.id.search_activity_clear_imageView)
    ImageView clearImageView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        editText.setFocusable(View.FOCUSABLE_AUTO);

        RxTextView.textChangeEvents(editText)
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        Log.d(TAG, "accept: ");
                        textView.setText(textViewTextChangeEvent.text());
                        if(textViewTextChangeEvent.text().length()>0){
                            clearImageView.setVisibility(View.VISIBLE);
                        }else {
                            clearImageView.setVisibility(View.GONE);
                        }
                    }
                });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    return true;
                }
                return false;
            }
        });


    }

    @OnClick(R.id.search_activity_clear_imageView)
    public void clickOnSearch(){
        Log.d(TAG, "clickOnSearch: ");
        editText.setText("");
        clearImageView.setVisibility(View.GONE);
    }
}
