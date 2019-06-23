package vrushali.com.vrushali_mankar_android_assignment.activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vrushali.com.vrushali_mankar_android_assignment.AndroidApplication;
import vrushali.com.vrushali_mankar_android_assignment.exceptions.ServerError;
import vrushali.com.vrushali_mankar_android_assignment.model.Product;
import vrushali.com.vrushali_mankar_android_assignment.model.ProductResponse;
import vrushali.com.vrushali_mankar_android_assignment.network.CommonNetworkService;

import java.util.ArrayList;

public class ProductsController {

    public interface ProductsControllerCallbacks {
        void onError(String message);

        void hideLoading();

        void onSuccess(ArrayList<Product> productArrayList, int totalProducts);
    }

    private ProductsControllerCallbacks controllerCallbacks;

    public ProductsController(ProductsControllerCallbacks controllerCallbacks) {
        this.controllerCallbacks = controllerCallbacks;
    }

    void getProductsList(int pageNumber) {
        CommonNetworkService commonNetworkService = AndroidApplication.getApplication().getNetworkServiceProvider().getCommonNetworkService();
        final Call<ProductResponse> responseBodyCall = commonNetworkService.getProducts(pageNumber, 30);
        responseBodyCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                controllerCallbacks.hideLoading();
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    controllerCallbacks.onSuccess(productResponse.getProductArrayList(), productResponse.getTotalProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                controllerCallbacks.hideLoading();
                if (t instanceof ServerError) {
                    ServerError serverError = (ServerError) t;
                    int httpStatusCode = serverError.getHttpStatusCode();
                    controllerCallbacks.onError("Api request failed with code : " + httpStatusCode);

                } else {
                    controllerCallbacks.onError("Api request failed");
                }
            }
        });
    }
}
