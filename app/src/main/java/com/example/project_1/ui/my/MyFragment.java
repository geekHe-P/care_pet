package com.example.project_1.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_1.databinding.FragmentMyBinding;
import com.example.project_1.ui.my.commontools.Consult;
import com.example.project_1.ui.my.commontools.CustomerService;
import com.example.project_1.ui.my.commontools.Data;
import com.example.project_1.ui.my.commontools.Difficult;
import com.example.project_1.ui.my.commontools.Encyclopedias;
import com.example.project_1.ui.my.commontools.Notice;
import com.example.project_1.ui.my.commontools.Personal;
import com.example.project_1.ui.my.commontools.Shopping;

import org.w3c.dom.Text;

public class MyFragment extends Fragment {

    private MyViewModel myViewModel;
private FragmentMyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        myViewModel =
                new ViewModelProvider(this).get(MyViewModel.class);

    binding = FragmentMyBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        //关注
        final TextView myFollow = binding.tvMyFollow;
        myFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myFollow","关注");
                Intent intent = new Intent(getContext(), MyFollow.class);
                getContext().startActivity(intent);

            }
        });


        //粉丝
        final TextView myFans = binding.tvMyFans;
        myFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myFans","粉丝");
                Intent intent = new Intent(getContext(), MyFans.class);
                getContext().startActivity(intent);

            }
        });


        //获赞与收藏
        final TextView myCollected = binding.tvMyCollected;
        myCollected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myCollected","获赞与收藏");
                Intent intent = new Intent(getContext(), MyCollected.class);
                getContext().startActivity(intent);

            }
        });


        //修改信息
        final TextView changeInfo = binding.tvChangeInfo;
            changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("changeInfo", "onClick: 编辑资料");

                Intent intent = new Intent(getContext(), ChangeInfo.class);
                getContext().startActivity(intent);

            }
        });



        //设置
        final ImageButton setting = binding.ibSetting;
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("setting","设置");
                Intent intent = new Intent(getContext(), Setting.class);
                getContext().startActivity(intent);

            }
        });


        //我的创作
        final ImageButton myCreation = binding.ibMyCreation;
        myCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myCreation","我的创作");
                Intent intent = new Intent(getContext(), MyCreation.class);
                getContext().startActivity(intent);

            }
        });



        //我的课程
        final ImageButton myCourse = binding.ibMyCourse;
        myCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myCourse","我的课程");
                Intent intent = new Intent(getContext(), MyCourse.class);
                getContext().startActivity(intent);

            }
        });


        //我的收藏
        final ImageButton myCollection = binding.ibMyCollection;
        myCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myCollection","我的收藏");
                Intent intent = new Intent(getContext(), MyCollection.class);
                getContext().startActivity(intent);

            }
        });


        //浏览记录
        final ImageButton browseRecords = binding.ibBrowseRecords;
        browseRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("browseRecords","浏览记录");
                Intent intent = new Intent(getContext(), BrowseRecords.class);
                getContext().startActivity(intent);

            }
        });

        //个人
        final ImageButton personal = binding.ibPersonal;
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("personal","个人");
                Intent intent = new Intent(getContext(), Personal.class);
                getContext().startActivity(intent);

            }
        });

        //咨询
        final ImageButton consult = binding.ibConsult;
        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("consult","咨询");
                Intent intent = new Intent(getContext(), Consult.class);
                getContext().startActivity(intent);

            }
        });


        //百科
        final ImageButton encyclopedias = binding.ibEncyclopedias;
        encyclopedias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("encyclopedias","百科");
                Intent intent = new Intent(getContext(), Encyclopedias.class);
                getContext().startActivity(intent);

            }
        });

        //客服
        final ImageButton customerService = binding.ibCustomerService;
        customerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("encyclopedias","客服");
                Intent intent = new Intent(getContext(), CustomerService.class);
                getContext().startActivity(intent);

            }
        });

        //购物
        final ImageButton shopping = binding.ibShopping;
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("shopping","购物");
                Intent intent = new Intent(getContext(), Shopping.class);
                getContext().startActivity(intent);

            }
        });

        //通知
        final ImageButton notice = binding.ibNotice;
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("notice","通知");
                Intent intent = new Intent(getContext(), Notice.class);
                getContext().startActivity(intent);

            }
        });

        //资料
        final ImageButton data = binding.ibData;
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("data","资料");
                Intent intent = new Intent(getContext(), Data.class);
                getContext().startActivity(intent);

            }
        });

        //疑难
        final ImageButton difficult = binding.ibDifficult;
        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("difficult","疑难");
                Intent intent = new Intent(getContext(), Difficult.class);
                getContext().startActivity(intent);

            }
        });

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}