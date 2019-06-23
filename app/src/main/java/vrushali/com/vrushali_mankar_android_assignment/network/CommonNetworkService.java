package vrushali.com.vrushali_mankar_android_assignment.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vrushali.com.vrushali_mankar_android_assignment.model.ProductResponse;

/**
 * Network task like login, register device, switch property , logout, TFA, Password reset, Internationalization
 * Created by Akash on 12/06/16.
 */
public interface CommonNetworkService {

    @GET("/walmartproducts/{pageNumber}/{pageSize}")
    Call<ProductResponse> getProducts(@Path("pageNumber") int pageNumber, @Path("pageSize") int pageSize);

}