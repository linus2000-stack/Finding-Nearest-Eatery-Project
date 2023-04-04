package com.example.hungryalpacas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.commons.io.FileUtils;

import java.io.File;

import java.io.IOException;
import java.util.*;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Gson gson = new Gson();
        File userdir = new File(this.getFilesDir(), "users");
        userdir.mkdir();

        //clear user dir
//        for (File user: userdir.listFiles()) {
//            user.delete();
//        }
//        Log.d("File", "All Users Cleared");

        if ((userdir.listFiles().length) == 0) {
            try {
                DefaultUser user = new DefaultUser();
                File defaultuserwrite = new File(userdir, user.getName() + ".json");
                if (defaultuserwrite.createNewFile()) {
                    String jsonout = gson.toJson(user);
                    FileUtils.writeStringToFile(defaultuserwrite, jsonout, "utf-8");
                    Log.i("File", "Default User created.");

                    //test user
                    try {
                        String jsonin = FileUtils.readFileToString(defaultuserwrite, "utf-8");
                        Log.d("File", "Default User Test: " + jsonin);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            catch (IOException e) {
                Log.e("File", "Default User creation IO Error");
                throw new RuntimeException(e);
            }
        }
        Log.d("File", "Userdir files: " + Arrays.toString(userdir.listFiles()));

        Button homeMapsButton = findViewById(R.id.homeMapsButton);
        homeMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeToMaps = new Intent(view.getContext(), MapsActivity.class);
                startActivity(homeToMaps);
            }
        });

        Button homeUsersButton = findViewById(R.id.homeUsersButton);
        homeUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeToUsers = new Intent(view.getContext(), UsersActivity.class);
                startActivity(homeToUsers);
            }
        });
    }
}