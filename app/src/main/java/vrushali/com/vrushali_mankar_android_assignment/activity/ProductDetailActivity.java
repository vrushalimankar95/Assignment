package vrushali.com.vrushali_mankar_android_assignment.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import vrushali.com.vrushali_mankar_android_assignment.R;
import vrushali.com.vrushali_mankar_android_assignment.databinding.ActivityProductDetailBinding;
import vrushali.com.vrushali_mankar_android_assignment.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private static final String ARG_PRODUCT = "PRODUCT";

    ActivityProductDetailBinding mBinding;

    public static Intent getLaunchIntent(Context context, Product product){
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(ARG_PRODUCT, product);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Details");
        initViews();
    }

    private void initViews() {
        Product product = getIntent().getParcelableExtra(ARG_PRODUCT);
        mBinding.setProductDetails(product);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
