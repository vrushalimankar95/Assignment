package vrushali.com.vrushali_mankar_android_assignment.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    public void showLoadingDialog(String message) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.cancel();
            }
            progressDialog = ProgressDialog.show(this, null, message, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelLoadingDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        cancelLoadingDialog();
        super.onDestroy();
    }
}
