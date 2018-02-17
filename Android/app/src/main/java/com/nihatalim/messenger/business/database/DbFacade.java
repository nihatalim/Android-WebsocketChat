package com.nihatalim.messenger.business.database;

import android.content.Context;

import com.nihatalim.messenger.business.database.models.Message;
import com.nihatalim.messenger.business.database.models.MyObjectBox;
import com.nihatalim.messenger.business.database.models.User;
import com.nihatalim.messenger.business.database.models.User_;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by thecower on 2/17/18.
 */

public class DbFacade {
    private BoxStore boxStore = null;

    public DbFacade(Context context) {
        this.boxStore = MyObjectBox.builder().androidContext(context).build();
    }

    public void saveMessage(com.nihatalim.messenger.dto.Message dtoMessage){
        User user = null;
        Message message = null;

        user = this.boxStore.boxFor(User.class).query().equal(User_.name, dtoMessage.getOwner().getName()).build().findFirst();
        if(user == null){
            user = new User();
            user.name = dtoMessage.getOwner().getName();
            user.sessionID = dtoMessage.getOwner().getSessionID();
            this.getUserBox().put(user);
        }

        message = new Message();
        message.message = dtoMessage.getMessage();
        message.date = dtoMessage.getDate();
        message.owner.setTarget(user);

        this.getMessageBox().put(message);
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    public Box<Message> getMessageBox(){
        return this.boxStore.boxFor(Message.class);
    }

    public Box<User> getUserBox(){
        return this.boxStore.boxFor(User.class);
    }


}
