package com.example.messengerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerapp.Models.Messages;
import com.example.messengerapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {

    ArrayList<Messages> messages;
    Context context;

    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<Messages> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        if(viewType == SENDER_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).getId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return  SENDER_VIEW_TYPE;
        }
        else{
            return  RECIEVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
     Messages message = messages.get(position);

     if(holder.getClass() == SenderViewHolder.class){
         ((SenderViewHolder)holder).senderMsg.setText(message.getMessage());
     }
     else{
         ((RecieverViewHolder)holder).recieverMsg.setText(message.getMessage());
     }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {

        TextView recieverMsg,recieverTime;

        public RecieverViewHolder(@NonNull  View itemView) {
            super(itemView);

            recieverMsg = itemView.findViewById(R.id.reciever_text);
        }

    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {

        TextView senderMsg,recieverTime;

        public SenderViewHolder(@NonNull  View itemView) {
            super(itemView);

           senderMsg = itemView.findViewById(R.id.sender_text);
        }

    }

}
