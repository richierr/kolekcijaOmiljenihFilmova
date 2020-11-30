package com.example.kolekcijaomiljenihfilmova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kolekcijaomiljenihfilmova.fragments.FavsListFragment;
import com.example.kolekcijaomiljenihfilmova.fragments.ListFragment;
import com.example.kolekcijaomiljenihfilmova.fragments.MainMoviesListFragment;
import com.example.kolekcijaomiljenihfilmova.fragments.SearchMovieFragment;
import com.example.kolekcijaomiljenihfilmova.fragments.SettingsFragment;
import com.example.kolekcijaomiljenihfilmova.utils.AppConstants;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListFragment.OnMovieItemClickInteface {
    private static final String CHANNEL_ID ="my_channel" ;
    private Toolbar myToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;

    Button dodajFilm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDrawer();
        navigateToScreen(AppConstants.SCREEN_FAV_MOVIES);
        dodajFilm=null;

    }


    private void setUpDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUpToolbar();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        Drawable toolbarDrawable = ContextCompat.getDrawable(this, R.drawable.ic_baseline_menu_24);
        actionBarDrawerToggle.setToolbarNavigationClickListener(v -> {
            if (mDrawerLayout.isDrawerOpen(navigationView)) {
                mDrawerLayout.closeDrawers();
            } else {
                mDrawerLayout.openDrawer(navigationView);
            }

        });
        myToolbar.setNavigationIcon(toolbarDrawable);
        actionBarDrawerToggle.syncState();


    }


    private void setUpToolbar() {
        myToolbar = findViewById(R.id.toolbar_reusable);
        setSupportActionBar(myToolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:

//            //Todo: implemntiraj ovo
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }
    @Override
    public void onMovieItemClick(int index) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (mDrawerLayout.isDrawerOpen(navigationView)) {
            mDrawerLayout.closeDrawer(navigationView);
        }
        if (item.getItemId() == R.id.show_favs) {
            navigateToScreen(AppConstants.SCREEN_FAV_MOVIES);
            return true;
        } else if (item.getItemId() == R.id.settings){
            navigateToScreen(AppConstants.SCREEN_SETTINGS);
            return true;
        }
        return false;
    }


    private void navigateToScreen(int screenFavMovies) {
        Fragment fragment;
        switch (screenFavMovies) {
            case AppConstants.SCREEN_FAV_MOVIES:



                myToolbar.removeView(dodajFilm);
                    dodajFilm = new Button(this);

                    Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                    l1.gravity = Gravity.START;
                    dodajFilm.setLayoutParams(l1);
                    dodajFilm.setText("Dodaj film");
                    dodajFilm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navigateToScreen(AppConstants.SCREEN_SEARCH);
                        }
                    });

                    myToolbar.addView(dodajFilm);


                fragment = new FavsListFragment();
                NotificationCompat.Builder builder=addNotifications();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                int notificationId=1232;
                notificationManager.notify(notificationId, builder.build());
                break;


                case AppConstants.SCREEN_SEARCH:
                    fragment=new SearchMovieFragment();
                    break;

            case AppConstants.SCREEN_SETTINGS:
                fragment = new SettingsFragment();
                break;
            default:
                fragment=new MainMoviesListFragment();


        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.placeholder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    private NotificationCompat.Builder addNotifications(){
        createNotificationChannel();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)
                .setAutoCancel(true);
        return builder;
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}