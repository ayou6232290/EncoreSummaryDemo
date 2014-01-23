package com.example.enoresummarydemo.api;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import cn.encore.lib.http.HttpConnectManager;
import cn.encore.lib.http.OnRequestCallback;
import cn.encore.lib.http.OnRequestListener;
import cn.encore.lib.http.Request;
import cn.encore.lib.json.JsonParser;
import cn.encore.lib.utils.NetCache;

import com.example.enoresummarydemo.bean.SingerTypeItem;
import com.example.enoresummarydemo.bean.SingerTypeVO;

public class HttpApi {

	/**
	 * 获取歌手列表
	 * 
	 * @param context
	 */
	public static final boolean madeSingerList(final Context context,final boolean isSaveCache, final OnRequestCallback cb) {
		final String url = HttpSetting.SINGER_LIST_URL;
		//如果是保存缓存,先重缓存里面
		boolean isHaveCache = false;
		if(isSaveCache){
			isHaveCache = callBackCache(context, url, cb);
		}
		Request request = new Request(url);
		request.setParser(new JsonParser(SingerTypeVO.class, false));
		request.setOnRequestListener(new OnRequestListener() {

			@Override
			public void onResponse(String url, int state, Object result, int type) {
				// TODO Auto-generated method stub
				if (cb != null) {
					if (state == HttpConnectManager.STATE_SUC && result != null && result instanceof SingerTypeVO) {
						if(isSaveCache){
							saveCache(context, result, url);
						}
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
		return isHaveCache;
	}
	
	/**
	 * 回调缓存
	 * @param url 保存的url
	 * @param cb callBack
	 */
	public static boolean callBackCache(Context context,String url,final OnRequestCallback cb)
	{
		boolean isHaveCache = false;
		Object object = NetCache.readCache(context, url);
		if (object != null && cb != null) {
			isHaveCache = true;
			cb.onSuccess(object);
		}
		return isHaveCache;
	}
	/**
	 * 保存缓存
	 * @param context
	 * @param object
	 * @param url
	 */
	public static void saveCache(Context context,Object object,String url){
		try {
			NetCache.saveCache(context, object, url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
