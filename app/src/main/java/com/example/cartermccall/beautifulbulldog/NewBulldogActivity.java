package com.example.cartermccall.beautifulbulldog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;

public class NewBulldogActivity extends AppCompatActivity {
    private EditText nameField, ageField;
    private ImageView imageField;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bulldog);

        nameField = (EditText) findViewById(R.id.newname_field);
        ageField = (EditText) findViewById(R.id.newage_field);
        imageField = (ImageView) findViewById(R.id.newimage_field);
        saveButton = (Button) findViewById(R.id.newsave_button);
        final Realm realm = Realm.getDefaultInstance();

        imageField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent,1);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameField.getText().toString().matches("")
                        && !ageField.getText().toString().matches("")
                        && imageField.getDrawable() != null){
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Bulldog bulldog = new Bulldog();
                            bulldog.setAge(ageField.getText().toString());
                            bulldog.setName(nameField.getText().toString());
                            bulldog.setId(realm.where(Bulldog.class).findAllSorted("id").last().getId() + 1);
                            BitmapDrawable image = (BitmapDrawable) imageField.getDrawable();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            image.getBitmap().compress(Bitmap.CompressFormat.JPEG,100,baos);
                            byte[] imageInByte = baos.toByteArray();
                            bulldog.setImage(imageInByte);
                            realm.copyToRealm(bulldog);
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageField.setImageBitmap(imageBitmap);
        }
    }
}
