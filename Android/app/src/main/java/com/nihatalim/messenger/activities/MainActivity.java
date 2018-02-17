package com.nihatalim.messenger.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.GsonBuilder;
import com.nihatalim.genericrecycle.business.GenericRecycleAdapter;
import com.nihatalim.genericrecycle.interfaces.OnBind;
import com.nihatalim.genericrecycle.interfaces.OnCreate;
import com.nihatalim.messenger.R;
import com.nihatalim.messenger.business.database.models.Message;
import com.nihatalim.messenger.business.database.models.Message_;
import com.nihatalim.messenger.business.interfaces.Connect;
import com.nihatalim.messenger.dto.Request;
import com.nihatalim.messenger.dto.request.MessageRequest;
import com.nihatalim.messenger.dto.response.MessageResponse;
import com.nihatalim.messenger.helpers.App;
import com.nihatalim.messenger.views.MessageHolder;
import com.nihatalim.messenger.views.MessageViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ImageView ivSend;
    private EditText etMessage;

    private GenericRecycleAdapter<MessageHolder, Message> recyclerAdapter = null;

    private SimpleDateFormat dateFormat = null;

    private MessageViewModel messageViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());

        this.recycler = findViewById(R.id.recycler);
        this.ivSend = findViewById(R.id.ivSend);
        this.etMessage = findViewById(R.id.etMessage);

        this.recyclerAdapter = new GenericRecycleAdapter<>(App.facade.getDbFacade().getMessageBox().query().orderDesc(Message_.date).build().find(0,50), getContext(), R.layout.message_element);

        this.recyclerAdapter.setOnCreateInterface(new OnCreate<MessageHolder>() {
            @Override
            public MessageHolder onCreate(ViewGroup viewGroup, int i, View view) {
                return new MessageHolder(view);
            }
        });

        this.recyclerAdapter.setOnBindInterface(new OnBind<MessageHolder>() {
            @Override
            public void OnBind(MessageHolder messageHolder, int i) {
                messageHolder.tvName.setText(recyclerAdapter.getObjectList().get(i).owner.getTarget().name);
                messageHolder.tvMessage.setText(recyclerAdapter.getObjectList().get(i).message);
                messageHolder.tvDate.setText(dateFormat.format(recyclerAdapter.getObjectList().get(i).date));
            }
        });

        this.recyclerAdapter.build(this.recycler);

        this.messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        this.messageViewModel.getMessageLiveData(App.facade.getDbFacade().getMessageBox()).observe(this, new Observer<List<com.nihatalim.messenger.business.database.models.Message>>() {
            @Override
            public void onChanged(@Nullable List<com.nihatalim.messenger.business.database.models.Message> messages) {
                recyclerAdapter.setObjectList(messages);
                recyclerAdapter.notifyDataSetChanged();
                recyclerAdapter.getLayoutManager().scrollToPosition(recyclerAdapter.getItemCount()-1);
            }
        });

        this.ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.nihatalim.messenger.dto.Message message = new com.nihatalim.messenger.dto.Message(App.user, etMessage.getText().toString(), new Date());

                Request request = new Request();
                request.setmRequestType(Request.RequestType.MessageRequest);
                request.setmRequest(new MessageRequest(message));
                App.service.getSocket().send(new GsonBuilder().setDateFormat("HH:mm:ss dd/MM/yyyy").create().toJson(request));
                etMessage.setText("");
            }
        });
    }


    private Context getContext(){
        return this;
    }
}