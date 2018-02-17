package com.nihatalim.messenger.views;

import android.arch.lifecycle.ViewModel;

import com.nihatalim.messenger.business.database.models.Message;
import com.nihatalim.messenger.business.database.models.Message_;

import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;

/**
 * Created by thecower on 2/17/18.
 */

public class MessageViewModel extends ViewModel {
    private ObjectBoxLiveData<Message> messageLiveData = null;

    public ObjectBoxLiveData<Message> getMessageLiveData(Box<Message> messageBox) {
        if(this.messageLiveData == null){
            this.messageLiveData = new ObjectBoxLiveData<>(messageBox.query().build());
        }
        return messageLiveData;
    }
}
