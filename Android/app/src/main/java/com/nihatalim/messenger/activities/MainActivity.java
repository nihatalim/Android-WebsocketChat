package com.nihatalim.messenger.activities;

import android.content.Context;
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
import com.nihatalim.messenger.business.interfaces.Connect;
import com.nihatalim.messenger.dto.Message;
import com.nihatalim.messenger.dto.Request;
import com.nihatalim.messenger.dto.request.MessageRequest;
import com.nihatalim.messenger.dto.response.MessageResponse;
import com.nihatalim.messenger.helpers.App;
import com.nihatalim.messenger.services.SocketService;
import com.nihatalim.messenger.views.MessageHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ImageView ivSend;
    private EditText etMessage;

    private List<Message> messages = null;

    private GenericRecycleAdapter<MessageHolder, Message> recyclerAdapter = null;

    private SimpleDateFormat dateFormat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());

        this.recycler = findViewById(R.id.recycler);
        this.ivSend = findViewById(R.id.ivSend);
        this.etMessage = findViewById(R.id.etMessage);

        this.messages = new ArrayList<>();
        this.recyclerAdapter = new GenericRecycleAdapter<>(this.messages, getContext(), R.layout.message_element);

        this.recyclerAdapter.setOnCreateInterface(new OnCreate<MessageHolder>() {
            @Override
            public MessageHolder onCreate(ViewGroup viewGroup, int i, View view) {
                return new MessageHolder(view);
            }
        });

        this.recyclerAdapter.setOnBindInterface(new OnBind<MessageHolder>() {
            @Override
            public void OnBind(MessageHolder messageHolder, int i) {
                messageHolder.tvName.setText(recyclerAdapter.getObjectList().get(i).getOwner().getName());
                messageHolder.tvMessage.setText(recyclerAdapter.getObjectList().get(i).getMessage());
                messageHolder.tvDate.setText(dateFormat.format(recyclerAdapter.getObjectList().get(i).getDate()));
            }
        });

        this.recyclerAdapter.build(this.recycler);

        this.recyclerAdapter.getLayoutManager().setReverseLayout(true);

        this.ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message message = new Message(App.user, etMessage.getText().toString(), new Date());

                Request request = new Request();
                request.setmRequestType(Request.RequestType.MessageRequest);
                request.setmRequest(new MessageRequest(message));
                App.service.getSocket().send(new GsonBuilder().setDateFormat("HH:mm:ss dd/MM/yyyy").create().toJson(request));
                etMessage.setText("");
            }
        });

        App.service.getInvoker().put("MessageResponse", new Connect<MessageResponse>() {
            @Override
            public void run(final MessageResponse response) {
                recyclerAdapter.getObjectList().add(response.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }


    private Context getContext(){
        return this;
    }
}