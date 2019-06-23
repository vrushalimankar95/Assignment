package vrushali.com.vrushali_mankar_android_assignment.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vrushali.com.vrushali_mankar_android_assignment.BuildConfig;
import vrushali.com.vrushali_mankar_android_assignment.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NetworkServiceProvider {

    private Retrofit retrofit;

    public NetworkServiceProvider() {
    }

    public Retrofit getRetrofit() {
        setupRetrofit();
        return retrofit;
    }

    private CommonNetworkService commonNetworkService;

    public CommonNetworkService getCommonNetworkService() {
        commonNetworkService = getRetrofit().create(CommonNetworkService.class);
        return commonNetworkService;
    }

    private void setupRetrofit() {
        String baseUrl = BuildConfig.BASE_URL;
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        AuthorizationInterceptor interceptor = new AuthorizationInterceptor();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();

        Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
    }


    private static class AuthorizationInterceptor implements Interceptor {
        AuthorizationInterceptor() {
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK);
            request = builder.build();
            return chain.proceed(request);
        }

    }

    public void resetNetwork() {
        this.retrofit = null;
        this.commonNetworkService = null;
    }
}
