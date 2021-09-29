package com.moringaschool.swagup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.moringaschool.swagup.adapters.SuggestedAdapter;
import com.moringaschool.swagup.adapters.WallpaperAdapter;
import com.moringaschool.swagup.models.SuggestedModel;
import com.moringaschool.swagup.models.WallpaperModel;
import com.moringaschool.swagup.interfaces.RecycleViewClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClothesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RecycleViewClickListener {

    static final float END_SCALE = 0.7f;
    ImageView menuIcon;
    LinearLayout contentView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView recyclerView, topMostRecyclerView;
    RecyclerView.Adapter adapter;
    WallpaperAdapter wallpaperAdapter;
    List<WallpaperModel>  wallpaperModelList;


    ArrayList<SuggestedModel> suggestedModels = new ArrayList<>();

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    ProgressBar progressBar;

    TextView replaceTitle;

    EditText searchEt;
    ImageView searchIv;

    int pageNumber = 1;

    String url = "https://api.pexels.com/v1/curated?page=" + pageNumber + "&per_page=80";
    private Object ImagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes2);

        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();
        //navigation drawer profile
        View headerView = navigationView.getHeaderView(0);

        recyclerView = findViewById(R.id.recycleView);
        topMostRecyclerView = findViewById(R.id.suggestedRecyclerView);

        wallpaperModelList = new ArrayList<>();
        wallpaperAdapter = new WallpaperAdapter(this, wallpaperModelList);

        recyclerView.setAdapter(wallpaperAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //scrolling behaviour
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    fetchWallpaper();
                }
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        replaceTitle = (TextView) findViewById(R.id.topMostTitle);

        fetchWallpaper();

        suggestedItems();


        searchEt = findViewById(R.id.searchEv);
        searchIv = findViewById(R.id.search_image);
        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClothesActivity.this, "search Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else {
                    drawerLayout.openDrawer(GravityCompat.START);

            }

                }
             });
        //animation in the drawer
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // scale the final view based on the current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //translate the view accounting of the scaled width
                final float xoffset = drawerView.getWidth() * slideOffset;
                final float xoffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xoffset - xoffsetDiff;
                contentView.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(this, "home clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_trending:
                Toast.makeText(this, "trending clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_most_viewed:
                Toast.makeText(this, "most viewed clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "logout clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about:
                Toast.makeText(this, "about clicked", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    private void suggestedItems() {
        topMostRecyclerView.setHasFixedSize(true);
        topMostRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        suggestedModels.add(new SuggestedModel(R.drawable.a, "Men casual"));
        suggestedModels.add(new SuggestedModel(R.drawable.b, "Women casual"));
        suggestedModels.add(new SuggestedModel(R.drawable.e, "Official wear"));
        suggestedModels.add(new SuggestedModel(R.drawable.d, "Trending"));
        suggestedModels.add(new SuggestedModel(R.drawable.e, "Men shoes"));
        suggestedModels.add(new SuggestedModel(R.drawable.f, "Women shoes"));
        suggestedModels.add(new SuggestedModel(R.drawable.g, "Fashion"));
        suggestedModels.add(new SuggestedModel(R.drawable.image3, "Beach wear"));
        suggestedModels.add(new SuggestedModel(R.drawable.image2, "Travel"));

        adapter = new SuggestedAdapter(suggestedModels, ClothesActivity.this);
        topMostRecyclerView.setAdapter(adapter);
    }

    private void fetchWallpaper() {
        //fetch image url and name from the pexels api

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);
                           try {
                            JSONObject JsonObject = new JSONObject(response);
                            JSONArray jsonArray = JsonObject.getJSONArray("photos");

                            int length = jsonArray.length();

                            for (int i = 0; i < length; i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                int id = object.getInt("id");
                                String photographerName = object.getString("photographer");

                                JSONObject objectImage = object.getJSONObject("src");
                                String originalUrl = objectImage.getString("original");
                                String mediumUrl = objectImage.getString("medium");



                                WallpaperModel wallpaperModel = new WallpaperModel(id, originalUrl, mediumUrl, photographerName);
                                wallpaperModelList.add(wallpaperModel);

                            }
                            wallpaperAdapter.notifyDataSetChanged();
                            pageNumber++;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization","563492ad6f91700001000001fbc5d384685a46609f2807fe967b7787");
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }

                        @Override
                        public void onItemClick (int position){
                            progressBar.setVisibility(View.VISIBLE);
                            if(position == 0) {
                                replaceTitle.setText("Men casual ");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=men casual";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }else if (position == 1) {
                                replaceTitle.setText("Women casual");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=women casual";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }else if (position == 2) {
                                replaceTitle.setText("official wear");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=official wear";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }else if (position == 3) {
                                replaceTitle.setText("trending");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=trending";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }else if (position == 4) {
                                replaceTitle.setText("beach wear");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=beach wear";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }else if (position == 5) {
                                replaceTitle.setText("men shoes");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=men shoes";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }else if (position == 6) {
                                replaceTitle.setText("women shoes");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=5&query=women shoes";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }else if (position == 7) {
                                replaceTitle.setText("fashion");
                                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=fashion";
                                wallpaperModelList.clear();
                                fetchWallpaper();
                                progressBar.setVisibility(View.GONE);
                            }

                        }


}
