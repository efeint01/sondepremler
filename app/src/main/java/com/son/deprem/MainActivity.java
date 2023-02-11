package com.son.deprem;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.son.deprem.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<DepremItem> arrayList = new ArrayList<>();
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        requestQueue = Volley.newRequestQueue(this);
        binding.swipeRefreshLy.setOnRefreshListener(this::getData);
        binding.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (!binding.swipeRefreshLy.isRefreshing()) {
                    binding.swipeRefreshLy.setEnabled(state == ViewPager.SCROLL_STATE_IDLE);
                }
            }
        });

        getData();

    }

    private void setViewPagerAdapter() {
        //set viewpager adapter
        DepremFragmentAdapter adapter = new DepremFragmentAdapter(this, arrayList);
        binding.pager.setAdapter(adapter);
        binding.dotsIndicator.attachTo(binding.pager);
    }

    private void getData() {
        arrayList.clear();
        int limit = 5;
        String url = "https://api.orhanaydogdu.com.tr/deprem/live.php?limit=" + limit;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, response -> {
            try {
                JSONArray data = response.getJSONArray("result");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.getJSONObject(i);
                    double mag = obj.getDouble("mag"); //magnitude
                    double lng = obj.getDouble("lng");  //longitude
                    double lat = obj.getDouble("lat"); //latitude
                    String lokasyon = obj.getString("lokasyon");
                    double depth = obj.getDouble("depth");
                    String title = obj.getString("title");
                    long timestamp = obj.getLong("timestamp");

                    arrayList.add(new DepremItem(
                            mag,
                            lng,
                            lat,
                            lokasyon,
                            depth,
                            title,
                            timestamp
                    ));

                }

                if (binding.swipeRefreshLy.isRefreshing()) {
                    binding.swipeRefreshLy.setRefreshing(false);
                }

                setViewPagerAdapter();

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonObjectRequest);

    }
}