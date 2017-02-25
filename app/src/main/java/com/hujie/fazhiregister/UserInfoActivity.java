package com.hujie.fazhiregister;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hujie.fazhiregister.base.UserBean;
import com.hujie.fazhiregister.databinding.ActivityUserInfoBinding;
import com.hujie.fazhiregister.mvp.getuser.IUserInfoView;
import com.hujie.fazhiregister.mvp.getuser.UserInfoPresenterImpl;
import com.hujie.fazhiregister.utils.LoadingDialog;
import com.hujie.fazhiregister.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends AppCompatActivity implements IUserInfoView {

    @BindView(R.id.gender)
    EditText gender;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    private UserInfoPresenterImpl presenter;
    private LoadingDialog dialog;
    private ActivityUserInfoBinding dataBinding;
    private static String path = "/sdcard/DemoHead/";
    private Bitmap head;//头像Bitmap


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        ButterKnife.bind(this);
        presenter = new UserInfoPresenterImpl(this);
        presenter.getUserInfo();
        ImageView imageView = new ImageView(getContext());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showUserData(UserBean userBean) {
        Log.i("=============", "showUserData: " + userBean.toString());
        dataBinding.setUser(userBean);
    }


    @Override
    public void showLoading() {
        dialog = new LoadingDialog(this);
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showError(String error) {
        ToastUtils.showErrorToast(this, error);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick({R.id.user_icon, R.id.get})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                Intent intent = new Intent(Intent.ACTION_PICK, null);//返回被选中项的URI
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
//                System.out.println("MediaStore.Images.Media.EXTERNAL_CONTENT_URI  ------------>   "
//                        + MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//   content://media/external/images/media
                startActivityForResult(intent, 1);
//                new AlertDialog.Builder(UserInfoActivity.this).setItems()
                break;
            case R.id.get:
                String age = dataBinding.age.getText().toString();
                String sex = dataBinding.gender.getText().toString();
                UserBean user = dataBinding.getUser();
                user.setAge(Integer.parseInt(age));
                user.setGender(Integer.parseInt(sex));
                presenter.updateUserInfo(user);
                break;
        }
    }
    private void setPicToView(Bitmap mBitmap) {
        if (Build.VERSION.SDK_INT>11){
            Paint paint = new Paint();
            paint.setAlpha(10);
            Canvas canvas = new Canvas();
        }
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd卡是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建以此File对象为名（path）的文件夹
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件（compress：压缩）

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从相册里面取相片的返回结果
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }

                break;
            //相机拍照后的返回结果
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            //调用系统裁剪图片后
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//保存在SD卡中
                        userIcon.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //找到指定URI对应的资源图片
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        //进入系统裁剪图片的界面
        startActivityForResult(intent, 3);
    }
}
