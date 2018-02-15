package com.nihatalim.messenger.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.nihatalim.messenger.R;

/**
 * Created by thecower on 2/15/18.
 */

public class MessageHolder extends RecyclerView.ViewHolder {
    public TextView tvName, tvDate, tvMessage;
    public MessageHolder(View itemView) {
        super(itemView);
        this.tvName = itemView.findViewById(R.id.tvName);
        this.tvDate = itemView.findViewById(R.id.tvDate);
        this.tvMessage = itemView.findViewById(R.id.tvMessage);
    }
}
