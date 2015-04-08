package com.wzq.security.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by wzqwsrf on 15/4/8.
 */
public class HttpUtil {

    public HttpUtil() {
    }

    /**
     * 从Request对象获取对应变量的值
     */
    public static String getParameter(HttpServletRequest request, String key, String default_value) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        String result = "";
        String value = request.getParameter(key);
        if (value == null || value.trim().equals("")) {
            result = default_value;
        } else {
            result = value.trim().replaceAll("'", "\\\\'");
            result = HttpUtil.filterDollarStr(result);
        }
        return result;
    }

    /**
     * 从Request对象获取对应变量的数组值
     */
    public static String[] getParameters(HttpServletRequest request, String key) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        String result[] = request.getParameterValues(key);

        if (result != null) {
            int rsize = result.length;
            for (int i = 0; i < rsize; i++) {
                result[i] = result[i].trim().replaceAll("'", "\\\\'");
                result[i] = HttpUtil.filterDollarStr(result[i]);
            }
        }

        return result;
    }

    /**
     * 用response在页面打印字符
     */
    public static void response(HttpServletResponse response, String str) throws Exception {
        response.setContentType("text/html;Charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(str);
        out.close();
    }

    /**
     * 用response在页面加入javascript代码
     */
    public static void script(HttpServletResponse response, StringBuffer resp_sb) throws Exception {
        response.setContentType("text/html;Charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        StringBuffer sb = new StringBuffer("");
        sb.append("<script language=javascript>");
        sb.append(resp_sb);
        sb.append("</script>");
        PrintWriter out = response.getWriter();
        out.write(sb.toString());
        out.close();
    }

    /**
     * 通过body来post数据
     */
    public static Map<String, String> postByBody(String api, String body, int conn_timeout, int read_timeout) {
        Map<String, String> result = new HashMap<String, String>();
        StringBuffer sb = new StringBuffer();
        int code = -1;
        try {

            // 创建连接
            URL url = new URL(api);
            HttpURLConnection url_conn = (HttpURLConnection) url.openConnection();
            url_conn.setConnectTimeout(conn_timeout);
            url_conn.setReadTimeout(read_timeout);
            url_conn.setDoOutput(true);
            url_conn.setDoInput(true);
            url_conn.setRequestMethod("POST");
            url_conn.setUseCaches(false);
            url_conn.setInstanceFollowRedirects(true);
//			url_conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            url_conn.connect();

            // POST请求
            DataOutputStream out = new DataOutputStream(url_conn.getOutputStream());
            out.writeBytes(body);
            out.flush();
            out.close();

            code = url_conn.getResponseCode();
            result.put("code", String.valueOf(code));

            if (code == 200) {

                // 读取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(url_conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                result.put("result", sb.toString());
                reader.close();
            }

            url_conn.disconnect();

        } catch (Exception ex) {
            result.put("code", String.valueOf(code));
            result.put("result", "{'err':'" + ex.getLocalizedMessage() + "','effect':''}");
            System.err.println(api + "\t" + ex);
            System.out.println(api + "\t" + ex);
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 通过form来post数据
     */
    public static Map<String, String> postByForm(String api, Map<String, String> params, int conn_timeout, int socket_timeout) {
        //String result = "";
        Map<String, String> result = new HashMap<String, String>();
        int code = -1;
        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, conn_timeout);// 120000;
            HttpConnectionParams.setSoTimeout(httpParameters, socket_timeout);
            HttpClient httpclient = new DefaultHttpClient(httpParameters);

            List<BasicNameValuePair> qparams = new ArrayList<BasicNameValuePair>();
            Iterator<String> key_value_it = params.keySet().iterator();
            while (key_value_it.hasNext()) {
                String key = key_value_it.next();
                String value = params.get(key);
                qparams.add(new BasicNameValuePair(key, value));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(qparams, "UTF-8");

            HttpPost httppost = new HttpPost(api);
            httppost.setEntity(entity);

            HttpResponse rsp = httpclient.execute(httppost);

            code = rsp.getStatusLine().getStatusCode();
            result.put("code", String.valueOf(code));

            if (code == 200) {
                result.put("result", EntityUtils.toString(rsp.getEntity()));
            }

        } catch (Exception ex) {
            result.put("code", String.valueOf(code));
            result.put("result", "{'err':'" + ex.getLocalizedMessage() + "','effect':''}");
            System.err.println(api + "\t" + ex);
            System.out.println(api + "\t" + ex);
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 处理$字符
     */
    public static String filterDollarStr(String str) {
        String sReturn = "";
        if (!StringUtils.trim(str).equals("")) {
            if (str.indexOf('$', 0) > -1) {
                while (str.length() > 0) {
                    if (str.indexOf('$', 0) > -1) {
                        // sReturn += str.subSequence(0, str.indexOf('$', 0));
                        // sReturn += "\\$";
                        // str = str.substring(str.indexOf('$', 0) + 1,str.length());
                    } else {
                        sReturn += str;
                        str = "";
                    }
                }
            } else {
                sReturn = str;
            }
        }
        return sReturn;
    }

}
