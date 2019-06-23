package vrushali.com.vrushali_mankar_android_assignment;

import android.app.Application;
import vrushali.com.vrushali_mankar_android_assignment.network.NetworkServiceProvider;

public class AndroidApplication extends Application {

    private NetworkServiceProvider networkServiceProvider;

    private static AndroidApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        application = null;
    }

    public static AndroidApplication getApplication() {
        return application;
    }

    public NetworkServiceProvider getNetworkServiceProvider() {
        if (networkServiceProvider == null) {
            networkServiceProvider = new NetworkServiceProvider();
        }
        return networkServiceProvider;
    }

    public void setNetworkServiceProvider(NetworkServiceProvider networkServiceProvider) {
        this.networkServiceProvider = networkServiceProvider;
    }

    public void resetNetwork() {
        getNetworkServiceProvider().resetNetwork();
    }

}
