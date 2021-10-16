package com.example.project_1.ui.dynamic;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.project_1.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DynamicFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private List<Dynamic> dynamicList = new ArrayList<>();
    private ImageView backgroundImage;
    private Uri imageUri;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        /*getActivity().requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);*/
        getActivity().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.dynamic_fragment_title);
        recyclerView = view.findViewById(R.id.recycler_view);
        DynamicAdapter adapter = new DynamicAdapter(dynamicList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        initDynamic();

        backgroundImage = view.findViewById(R.id.dynamic_fragment_background);
        backgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });
        return view;
    }

    private void initDynamic() {
        Dynamic dynamic1 = new Dynamic("jerry-jing",R.mipmap.icon,"狗狗真可爱!狗狗真可爱!狗狗真可爱!狗狗真可爱!狗狗真可爱!狗狗真可爱!狗狗真可爱!",R.drawable.dog);
        dynamicList.add(dynamic1);
        for(int i = 0; i < 20; i++) {
            Dynamic dynamic = new Dynamic("jerry-jing",R.mipmap.icon,"狗狗真可爱!",R.drawable.dog);
            dynamicList.add(dynamic);
        }
    }

    private void changeImage() {

        final String font[] = { "拍照", "从图库选择" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(font, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(font[which]){
                    case "拍照":
//                        Toast.makeText(getContext(),"You click '拍照' ",Toast.LENGTH_SHORT).show();
                        File outputImage = new File(getContext().getExternalCacheDir(),"output_image.jpg");
                        try{
                            if(outputImage.exists()) {
                                outputImage.createNewFile();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(Build.VERSION.SDK_INT >= 24) {
                            imageUri = FileProvider.getUriForFile(getContext(),"com.example.cameraalbumtest.fileprovider",outputImage);
                        } else {
                            imageUri = Uri.fromFile(outputImage);
                        }
                        Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent1.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        startActivityForResult(intent1,1);
                        break;
                    case "从图库选择":
//                        Toast.makeText(getContext(),"You click '从图库选择' ",Toast.LENGTH_SHORT).show();
                        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                        } else {
                            Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
                            intent2.setType("image/*");
                            startActivityForResult(intent2,2);
                        }
                }
            }
        }).setNegativeButton("Cancle", null)
                .setPositiveButton("OK", null).create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //选择拍照
            case 1:
                try{
                    Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
                    backgroundImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            //选择图库
            case 2:
                //4.4以上版本
                if(Build.VERSION.SDK_INT >= 19) {
                    handleImageOnkitKat(data);
                } else { //4.4以下版本
                    handleImageBeforeKitKat(data);
                }
                break;
            default:
                break;
        }
    }

    //4.4以上系统处理图片
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnkitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(getContext(), uri)) {
            //如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    //4.4以下系统处理图片
    private void handleImageBeforeKitKat(Intent data) {

    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContext().getContentResolver().query(uri, null, selection,null, null);
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if(imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            backgroundImage.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }
}