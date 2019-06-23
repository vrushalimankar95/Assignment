package vrushali.com.vrushali_mankar_android_assignment.model;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.*;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;
import vrushali.com.vrushali_mankar_android_assignment.AndroidApplication;
import vrushali.com.vrushali_mankar_android_assignment.BuildConfig;
import vrushali.com.vrushali_mankar_android_assignment.R;

public class Product implements Parcelable {

    @SerializedName("productId")
    private String productId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("longDescription")
    private String longDescription;

    @SerializedName("price")
    private String price;

    @SerializedName("productImage")
    private String productImage;

    @SerializedName("reviewRating")
    private double reviewRating;

    @SerializedName("reviewCount")
    private int reviewCount;

    @SerializedName("inStock")
    private boolean inStock;

    protected Product(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        shortDescription = in.readString();
        longDescription = in.readString();
        price = in.readString();
        productImage = in.readString();
        reviewRating = in.readDouble();
        reviewCount = in.readInt();
        inStock = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Spanned getShortDescription() {
        if (shortDescription != null && !shortDescription.isEmpty()) {
            return Html.fromHtml(shortDescription);
        }
        return new SpannableString("");
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Spanned getLongDescription() {
        if (longDescription != null && !longDescription.isEmpty()) {
            longDescription = "Description : \n" + Html.fromHtml(longDescription);

            SpannableStringBuilder str = new SpannableStringBuilder(longDescription);
            str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return str;
        }
        return new SpannableString("");
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        if (productImage != null && !productImage.isEmpty()) {
            return BuildConfig.BASE_URL + productImage;
        }
        return "";
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(view.getContext()).load(imageUrl).placeholder(R.drawable.progress_animation).into(view);
        }
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public float getReviewRating() {
        return (float) reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Spanned isInStock() {
        if (inStock){
            String text = "In stock";
            Spannable spannable = new SpannableString(text);
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(AndroidApplication.getApplication(),R.color.colorAccent)), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannable;

        }else {
            String text = "Out of stock";
            Spannable spannable = new SpannableString(text);
            spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannable;
        }
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productId);
        parcel.writeString(productName);
        parcel.writeString(shortDescription);
        parcel.writeString(longDescription);
        parcel.writeString(price);
        parcel.writeString(productImage);
        parcel.writeDouble(reviewRating);
        parcel.writeInt(reviewCount);
        parcel.writeByte((byte) (inStock ? 1 : 0));
    }
}
