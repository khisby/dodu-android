package id.khisoft.dodu.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import id.khisoft.dodu.home_screen;
import id.khisoft.dodu.login_screen;
import id.khisoft.dodu.utils.ConfigApi;

import static android.content.Context.MODE_PRIVATE;

public class AkunController {
    private Context ctx;
    private RequestQueue queue;
    private Activity activity;
    private String url;
    SharedPreferences pref;

    public AkunController(Activity activity) {
        this.url = ConfigApi.url;
        this.activity = activity;
        this.ctx = activity.getApplicationContext();
        queue = Volley.newRequestQueue(this.ctx);
        pref = this.ctx.getSharedPreferences("UserLoginDodu", MODE_PRIVATE);
    }

    public void Login(String surelPengguna, String sandiPengguna){
        String url = this.url + "login/login";
        JSONObject params = new JSONObject();
        try {
            params.put("surelPengguna", surelPengguna);
            params.put("sandiPengguna",sandiPengguna);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        queue.add(new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("status") == 200){
                        JSONObject data = new JSONObject(response.getString("data"));

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("token", data.getString("TOKEN"));
                        editor.putString("nama", data.getString("NAMA_PENGGUNA"));
                        editor.putString("surel", data.getString("SUREL_PENGGUNA"));
                        editor.apply();

                        Intent i = new Intent(ctx, home_screen.class);
                        activity.startActivity(i);
                        activity.finish();
                    }else{
                        Toast.makeText(ctx, response.getString("pesan"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.getMessage());
            }
        }));

    }

    public void Logout(){
        String url = this.url + "logout/logout";
        JSONObject params = new JSONObject();
        try {
            params.put("token", pref.getString("token", null));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        queue.add(new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("status") == 200){
                        System.out.println("sampai sini");
                        SharedPreferences.Editor editor = pref.edit();
                        editor.clear();
                        editor.apply();

                        Intent i = new Intent(ctx, login_screen.class);
                        activity.startActivity(i);
                        activity.finish();
                    }else{
                        Toast.makeText(ctx, response.getString("pesan"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.getMessage());
            }
        }));

    }

    public void Register(String namaPengguna, String surelPengguna, String sandiPengguna){
        String url = this.url + "registrasi/register";
        JSONObject params = new JSONObject();
        try {
            params.put("namaPengguna", namaPengguna);
            params.put("surelPengguna", surelPengguna);
            params.put("sandiPengguna",sandiPengguna);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        queue.add(new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("status") == 201){
                        Intent i = new Intent(ctx, login_screen.class);
                        i.putExtra("pesan", response.getString("pesan"));
                        activity.startActivity(i);
                        activity.finish();
                    }else{
                        Toast.makeText(ctx, response.getString("pesan"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.getMessage());
            }
        }));

    }
}
