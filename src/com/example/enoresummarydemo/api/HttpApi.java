package com.example.enoresummarydemo.api;

import android.content.Context;
import cn.encore.lib.http.HttpConnectManager;
import cn.encore.lib.http.OnRequestCallback;
import cn.encore.lib.http.OnRequestListener;
import cn.encore.lib.http.Request;
import cn.encore.lib.json.JsonParser;

import com.example.enoresummarydemo.bean.SingerTypeVO;

public class HttpApi {

	/**
	 * 获取歌手列表
	 * 
	 * @param context
	 */
	public static final void madeSingerList(Context context, final OnRequestCallback cb) {
		String url = HttpSetting.SINGER_LIST_URL;
		Request request = new Request(url);
		request.setParser(new JsonParser(SingerTypeVO.class, false));
		request.setOnRequestListener(new OnRequestListener() {

			@Override
			public void onResponse(String url, int state, Object result, int type) {
				// TODO Auto-generated method stub
				if (cb != null) {
					if (state == HttpConnectManager.STATE_SUC && result != null && result instanceof SingerTypeVO) {
						cb.onSuccess(result);
					} else if (state == HttpConnectManager.STATE_TIME_OUT) { // 处理超时
						cb.onFail("time out", state);
					} else { // 其他错误
						cb.onFail("request fail", state);
					}
				}

			}
		});
		HttpConnectManager.getInstance(context.getApplicationContext()).doGet(request);
	}
}
