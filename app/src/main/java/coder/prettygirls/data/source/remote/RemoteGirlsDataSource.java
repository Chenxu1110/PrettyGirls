package coder.prettygirls.data.source.remote;

import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.http.GirlsRetrofit;
import coder.prettygirls.http.GirlsService;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oracleen on 2016/6/29.
 */
public class RemoteGirlsDataSource implements GirlsDataSource {

    @Override
    public void getGirls(LoadGirlsCallback callback) {

    }

    @Override
    public void getGirl(final GetGirlCallback callback) {
        GirlsRetrofit.getRetrofit()
                .create(GirlsService.class)
                .getGirls("福利", 1, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(GirlsBean girlsBean) {
                        callback.onGirlLoaded(girlsBean);
                    }
                });
    }
}
