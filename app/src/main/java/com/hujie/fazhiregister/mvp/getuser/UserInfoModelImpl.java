package com.hujie.fazhiregister.mvp.getuser;

import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.UserBean;
import com.hujie.fazhiregister.net.NetUtil;
import com.hujie.fazhiregister.utils.LocalData;

import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by hujie on 2017/2/23.
 */

public class UserInfoModelImpl implements IUserInfoModel {
    @Override
    public Observable<BaseBean<UserBean>> getUser(int userId) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        RequestBody codeBody = NetUtil.getCodeBody(map);
        return NetUtil.getInstance().getIApi().getUser(codeBody);
    }

    @Override
    public Observable<BaseBean<UserBean>> UpdateUser(String nickname, String icon, int gender, int age, String province, String city, String trade, String company, String job, String fullname) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("user_id", LocalData.getUserId());
        map.put("nickname",nickname);
        map.put("icon",icon);
        map.put("gender",gender);
        map.put("age",age);
        map.put("province",province);
        map.put("city",city);
        map.put("trade",trade);
        map.put("company",company);
        map.put("job",job);
        map.put("fullname",fullname);
        RequestBody codeBody = NetUtil.getCodeBody(map);
        return NetUtil.getInstance().getIApi().updateUser(codeBody);
    }

}
