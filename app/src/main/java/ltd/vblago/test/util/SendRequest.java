package ltd.vblago.test.util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import ltd.vblago.test.model.Comment;

public class SendRequest extends AsyncTask<String, Void, String> {

    private Comment comment;

    public SendRequest(Comment comment) {
        this.comment = comment;
    }

    protected String doInBackground(String... arg0) {

        try {

            URL url = new URL("https://script.google.com/macros/s/AKfycbymEW6A9ezLdeuhDp0ttGpjhkFsecjmaAM5m0e_hT238RYjY2g/exec");
            JSONObject postDataParams = new JSONObject();

            String id = "1EbPISAaL9YH9eac8lBq4XXg-TVw_8WinMKu66qpUmtM";

            postDataParams.put("date", comment.date);
            postDataParams.put("point", comment.point);
            postDataParams.put("great", comment.great);
            postDataParams.put("good", comment.good);
            postDataParams.put("fine", comment.fine);
            postDataParams.put("bad", comment.bad);
            postDataParams.put("range", comment.range);
            postDataParams.put("quality", comment.quality);
            postDataParams.put("price", comment.price);
            postDataParams.put("id", id);

            Log.e("params", postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder("");
                String line;

                while ((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            } else {
                return "false : " + responseCode;
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }

    private String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}

