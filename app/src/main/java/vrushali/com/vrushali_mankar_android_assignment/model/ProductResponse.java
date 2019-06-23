package vrushali.com.vrushali_mankar_android_assignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResponse{

    @SerializedName("products")
    private ArrayList<Product> productArrayList;

    @SerializedName("totalProducts")
    private int totalProducts;

    @SerializedName("pageNumber")
    private int pageNumber;

    @SerializedName("pageSize")
    private int pageSize;

    @SerializedName("statusCode")
    private int statusCode;

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
