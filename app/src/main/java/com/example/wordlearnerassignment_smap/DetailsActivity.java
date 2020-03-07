package com.example.wordlearnerassignment_smap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

private Button cancel;
private Button edit;
private ImageView PictureOfWord;
private TextView NameOfWord;
private TextView PronounOfWord;
private TextView DescripOfWord;
private TextView NotesOfWord;
private TextView RatingOfWord;

static final int BETWEEN_LIST_DETAIL_RES = 99;
static final int BETWEEN_DETAIL_EDIT_RES = 98;
static final int BETWEEN_DETAIL_EDIT_REQ = 102;
WordTemplate word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (savedInstanceState != null){
            word = savedInstanceState.getParcelable("word");
        }
        else {
            Intent receivedFromList = getIntent();
            word = receivedFromList.getParcelableExtra("word");
        }
        //Bind UI elements with local variables
        PictureOfWord = findViewById( R.id.PictureOfWord_Detail);
        NameOfWord = findViewById( R.id.NameOfWord_Detail);
        PronounOfWord = findViewById( R.id.PronounOfWord_Detail);
        DescripOfWord = findViewById( R.id.DescriptionOfWord_Detail);
        NotesOfWord = findViewById( R.id.NotesOfWord_Detail);
        RatingOfWord = findViewById( R.id.RatingOfWord_Detail);
        cancel = findViewById(R.id.ACTIVITY_DETAIL_CANCEL_BUTTON);
        edit = findViewById(R.id.ACTIVITY_DETAIL_BUTTON_EDIT);

        //Set the data into the UI elements
        PictureOfWord.setImageResource(word.getImageOfWord());
        NameOfWord.setText(word.getNameOfWord());
        PronounOfWord.setText(word.getPronounOfWord());
        DescripOfWord.setText(word.getDescripOfWord());
        NotesOfWord.setText(word.getNotesOfWord());
        RatingOfWord.setText(String.valueOf(word.getRatingOfWord()));
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //SRC:https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
        //Send data to edit activity
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToEdit = new Intent(DetailsActivity.this, EditActivity.class);
                GoToEdit.putExtra("word",word);
                //Receive Data With finished activity
                startActivityForResult(GoToEdit,BETWEEN_DETAIL_EDIT_REQ);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent receivedData)
    {
        super.onActivityResult(requestCode , resultCode , receivedData);
        if (requestCode == BETWEEN_DETAIL_EDIT_REQ){
            if (resultCode == BETWEEN_DETAIL_EDIT_RES)
            {
                Intent returner = receivedData;

                setResult(BETWEEN_LIST_DETAIL_RES,returner);
                finish();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
            outState.putParcelable("word",word);
    }
}
