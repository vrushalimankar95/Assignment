package vrushali.com.vrushali_mankar_android_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import vrushali.com.vrushali_mankar_android_assignment.R;
import vrushali.com.vrushali_mankar_android_assignment.adapter.ProductListAdapter;
import vrushali.com.vrushali_mankar_android_assignment.model.Product;
import vrushali.com.vrushali_mankar_android_assignment.utils.ItemClickSupport;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements ItemClickSupport.OnItemClickListener, ProductsController.ProductsControllerCallbacks, ProductListAdapter.OnBottomReachedListener {

    private RecyclerView recyclerView;

    private TextView txvNoDataFound;

    private ProductListAdapter propertyListAdapter;

    private int pageNumber = 1;

    private int totalProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setUpViews();
        showLoadingDialog("Loading...");
        getProducts(pageNumber);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_products);
        recyclerView.onScrollStateChanged(1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txvNoDataFound = findViewById(R.id.txv_no_data_found);
    }

    private void setUpViews() {
        propertyListAdapter = new ProductListAdapter(getApplicationContext(), new ArrayList<Product>());
        propertyListAdapter.setOnBottomReachedListener(this);
        recyclerView.setAdapter(propertyListAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this);
    }

    private void getProducts(int pageNumber) {
        ProductsController productsController = new ProductsController(this);
        productsController.getProductsList(pageNumber);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        ArrayList<Product> products = propertyListAdapter.getmObjects();
        if (products != null && position < products.size()) {
            Product product = products.get(position);
            Intent intent = ProductDetailActivity.getLaunchIntent(getApplicationContext(), product);
            startActivity(intent);
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        cancelLoadingDialog();
    }

    @Override
    public void onSuccess(ArrayList<Product> productArrayList, int totalProducts) {
        this.totalProducts = totalProducts;
        this.pageNumber++;
        Toast.makeText(this, "Products loaded successfully", Toast.LENGTH_SHORT).show();
        ArrayList<Product> products = propertyListAdapter.getmObjects();
        products.addAll(products.size(), productArrayList);

        if (products.isEmpty()) {
            txvNoDataFound.setVisibility(View.VISIBLE);
        }else {
            txvNoDataFound.setVisibility(View.GONE);
        }
        propertyListAdapter.setObjects(products);
        propertyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBottomReached(int position) {
        if (totalProducts != propertyListAdapter.getItemCount()) {
            getProducts(pageNumber);
        }
    }
}
