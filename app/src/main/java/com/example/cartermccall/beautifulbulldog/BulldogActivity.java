package com.example.cartermccall.beautifulbulldog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import io.realm.Realm;

public class BulldogActivity extends AppCompatActivity {

    private TextView nameField;
    private TextView ageField;
    private Button voteButton;
    private RadioGroup ratingGroup;
    private int ratingID, rating;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        nameField = (TextView) findViewById(R.id.name_field);
        ageField = (TextView) findViewById(R.id.age_field);
        Realm realm = Realm.getDefaultInstance();
        String username = (String) getIntent().getStringExtra("username");
        final User user = realm.where(User.class).equalTo("username", username).findFirst();

        String id = (String) getIntent().getStringExtra("bulldog");
        final Bulldog bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();

        nameField.setText(bulldog.getName());
        ageField.setText(bulldog.getAge());
        voteButton = (Button) findViewById(R.id.vote_button);

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();
                ratingGroup = (RadioGroup) findViewById(R.id.rating);
                ratingID = ratingGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(ratingID);
                rating = Integer.parseInt(radioButton.getText().toString());

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Vote vote = new Vote();
                        vote.setOwner(user);
                        vote.setBulldog(bulldog);
                        vote.setRating(rating);
                        realm.copyToRealm(vote);

                        finish();
                    }
                });

            }
        });
    }
}
