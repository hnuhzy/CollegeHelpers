package com.example.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.BundleCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSE_PHOTO  = 2 ;
    private static final int TAKE_PHOTO  = 100 ;
    private  TextView textView;
     private ImageButton return1;
     private  ImageButton history;
     private Button takepicture;
     private Uri uri;
     private ImageView picture;
     private  Button searchbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        textView  = findViewById(R.id.Input);
        return1 = (ImageButton) findViewById(R.id.left);
        history = (ImageButton) findViewById(R.id.history);
        takepicture = (Button) findViewById(R.id.takepicture);
        searchbutton = (Button)findViewById(R.id.search_button);
        //返回按键
        return1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //历史记录按键
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,History.class);
                intent.putExtra("activity_name","Mainactivity");
                startActivity(intent);
                finish();
            }
        });
        //搜索按键
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Message.class);
                intent.putExtra("activity_name","Mainactivity");
                startActivity(intent);
                finish();
            }
        });

        //拍照搜题按键
        takepicture.setOnClickListener(this);



    }
    //用于判断和执行拍照还是选取照片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK){
                    if (Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    //选取相片
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagepath = getImagepath(uri,null);
        displayImage(imagepath);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        String imagepath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            String docid = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docid.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagepath = getImagepath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){

                Uri contenturi = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docid));
                imagepath = getImagepath(contenturi,null);

            }else if ("content".equalsIgnoreCase(uri.getScheme())){
                imagepath = getImagepath(uri,null);
            }else if ("file".equalsIgnoreCase(uri.getScheme())){
                imagepath = uri.getPath();
            }
            displayImage(imagepath);
        }
    }
    //显示图片,这部分还没实现，暂时显示不了
    private void displayImage(String imagepath) {
        if (imagepath != null){
            Bitmap bitmap  = BitmapFactory.decodeFile(imagepath);
            picture.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"获取相片失败！",Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagepath(Uri externalContentUri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
            }
        }
        return  path;
    }
    //拍照搜题时弹出的选项框设置
    private  void setDialog(){
        Dialog selectedmenu = new Dialog(this,R.style.BottomDialog);
        //获取布局文件
        RelativeLayout root = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.selected_menu, null);
        //初始化视图
        root.findViewById(R.id.xiangji).setOnClickListener((View.OnClickListener) this);
        root.findViewById(R.id.tuku).setOnClickListener((View.OnClickListener) this);
        selectedmenu.setContentView(root);
        Window dialogWindow = selectedmenu.getWindow();
        //从屏幕底下弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        selectedmenu.show();

    }
    //为选项框设置事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takepicture:
                setDialog();
                break;
            case R.id.xiangji:
                takepicture();
                break;
            case  R.id.tuku:
                selectpicture();
                break;
            default:
                break;
        }
    }
    //拍照
    private void takepicture(){
        File outputImage = new File(getExternalCacheDir(),"ouputimage.jpg");
        try{
            if (outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT>=24){
            uri = FileProvider.getUriForFile(MainActivity.this,"com.example.search.fileprovider",outputImage);
        }else {
            uri = Uri.fromFile(outputImage);
        }

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,TAKE_PHOTO);
    }
    //从相册选择
    private void  selectpicture(){
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else {
                openAlbum();
            }
    }

    private void openAlbum() {
        //Intent intent = new Intent("android.intent.action.GET_CONTENT");
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }
                break;
            default:
                break;
        }
    }
}
