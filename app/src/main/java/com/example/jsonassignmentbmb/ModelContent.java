package com.example.jsonassignmentbmb;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelContent
{
    public static final List<Model> MODELS = new ArrayList<>();
    public static final Map<String, Model> MODELS_MAP = new HashMap<>();
    public static boolean BUILT = false;

    public void jsonParse(Activity activity)
    {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = activity.getString(R.string.JSON_url);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("gameCompanies");
                            MODELS.clear();
                            MODELS_MAP.clear();
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject gameCompany = jsonArray.getJSONObject(i);
                                String name = gameCompany.getString("name");
                                Integer year = gameCompany.getInt("year");
                                String recentConsole = gameCompany.getString("recentConsole");
                                Model model = new Model(name, year, recentConsole);
                                MODELS.add(model);
                                MODELS_MAP.put(name, model);
                            }
                            if(!BUILT)
                                activity.recreate();
                            BUILT = true;
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });

        queue.add(objectRequest);
    }
}
