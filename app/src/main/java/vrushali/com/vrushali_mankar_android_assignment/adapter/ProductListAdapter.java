package vrushali.com.vrushali_mankar_android_assignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import vrushali.com.vrushali_mankar_android_assignment.R;
import vrushali.com.vrushali_mankar_android_assignment.model.Product;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.PropertyViewHolder> {

    private ArrayList<Product> mObjects;

    private Context context;

    private OnBottomReachedListener onBottomReachedListener;

    public interface OnBottomReachedListener {
        void onBottomReached(int position);
    }

    public ProductListAdapter(Context context, ArrayList<Product> mObjects) {
        this.mObjects = mObjects;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListAdapter.PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_list_item, parent, false);
        return new ProductListAdapter.PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.PropertyViewHolder holder, int position) {
        Product miniProperty = mObjects.get(position);
        String url = miniProperty.getImageUrl();
        if (!url.isEmpty()) {
            Picasso.with(context).load(url).placeholder(R.drawable.progress_animation).into(holder.imgProduct);
        }
        holder.txvName.setText(miniProperty.getProductName());
        holder.txvDesc.setText(miniProperty.getShortDescription());
        holder.txvPrice.setText(miniProperty.getPrice());
        holder.ratingBar.setRating(miniProperty.getReviewRating());

        if (position == mObjects.size() - 1) {
            onBottomReachedListener.onBottomReached(position);
        }
    }

    @Override
    public int getItemCount() {
        return mObjects == null ? 0 : mObjects.size();
    }

    public void setObjects(ArrayList<Product> mObjects) {
        this.mObjects = mObjects;
    }

    public ArrayList<Product> getmObjects() {
        return mObjects;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {
        TextView txvName, txvDesc, txvPrice;
        ImageView imgProduct;
        RatingBar ratingBar;

        public PropertyViewHolder(View itemView) {
            super(itemView);
            txvPrice = itemView.findViewById(R.id.txv_price);
            txvDesc = itemView.findViewById(R.id.txv_description);
            txvName = itemView.findViewById(R.id.txv_product_name);
            imgProduct = itemView.findViewById(R.id.img_product_image);
            ratingBar = itemView.findViewById(R.id.rating);

        }
    }
}
