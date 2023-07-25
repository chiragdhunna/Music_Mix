package com.example.mymusic;

import static android.os.Environment.getExternalStoragePublicDirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        ArrayList<File> mySongs = new ArrayList<>();
                        String[] proj = { MediaStore.Audio.Media._ID,MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA };

                        Cursor audioCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);

                        if(audioCursor != null){
                            if(audioCursor.moveToFirst()){
                                do{
                                    int audioIndex = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                                    int dataIndex = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);

                                    String audioPath = audioCursor.getString(dataIndex);
                                    mySongs.add(new File(audioPath));
                                }while(audioCursor.moveToNext());
                            }
                        }
                        audioCursor.close();

                        String[] items = new String[mySongs.size()];
                        for(int i=0; i<mySongs.size(); i++){
                            File audioFile = mySongs.get(i);
                            String fileName = audioFile.getName().replace(".mp3", "");
                            items[i] = fileName;
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1, items);
                        listView.setAdapter(adapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(MainActivity.this,MusicSystem.class);
                                String CurrentSong = listView.getItemAtPosition(position).toString();
                                intent.putExtra("SongList",mySongs);
                                intent.putExtra("CurrentSong",CurrentSong);
                                intent.putExtra("position",position);
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

//    public ArrayList<File> fetchSongs(File file) {
//        ArrayList arrayList = new ArrayList();
//        File[] songs = file.listFiles();
//        if (songs != null) {
//            for (File myFile : songs) {
//                if (!myFile.isHidden() && myFile.isDirectory()) {
//                    arrayList.addAll(fetchSongs(myFile));
//                } else {
//                    if ((myFile.getName().endsWith(".mp3") || myFile.getName().endsWith(".wav")) && !myFile.getName().endsWith(".")) ;
//                    {
//                        arrayList.add(myFile);
//                    }
//                }
//            }
//        }
//        return arrayList;
//    }

}