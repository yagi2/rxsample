package com.yagi2.rxsample.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yagi2.rxsample.R;
import com.yagi2.rxsample.adapter.CardRecyclerViewAdapter;
import com.yagi2.rxsample.api.WeatherApi;
import com.yagi2.rxsample.model.WeatherModel;

import org.joda.time.DateTime;

import java.util.concurrent.CancellationException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends RxAppCompatActivity {

    private final String BASE_URL = "http://weather.livedoor.com/forecast/webservice/";
    private final String CITY_NUMBER = "140010";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text)
    TextView mTextView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.publish_time)
    TextView mPublishTimeText;

    private CardRecyclerViewAdapter mCardRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardRecyclerViewAdapter = new CardRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mCardRecyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        retrofit.create(WeatherApi.class).getWeather(CITY_NUMBER)
                .subscribeOn(Schedulers.io())
                .toSingle()
                .compose(this.bindToLifecycle().<WeatherModel>forSingle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<WeatherModel>() {
                    @Override
                    public void onSuccess(WeatherModel weather) {
                        mToolbar.setTitle(weather.title);

                        DateTime dateTime = new DateTime(weather.description.publicTime);

                        mTextView.setText(weather.description.text);
                        mPublishTimeText.setText("発表時間 : " + dateTime.toString("yyyy/MM/dd HH:mm:ss"));

                        mCardRecyclerViewAdapter.setData(weather.forecasts);
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                        if (e instanceof CancellationException) {
                            unsubscribe();
                            return;
                        }
                    }
                });
    }
}
