package com.example.groupa_capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import javax.annotation.Nonnull;

import io.realm.Realm;
import io.realm.RealmResults;

public class thisAdaptor extends RecyclerView.Adapter<thisAdaptor.viewholder> {

    Context context;
    RealmResults<Note> notelist;

    public thisAdaptor(Context context, RealmResults<Note> notelist) {
        this.context = context;
        this.notelist = notelist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.noteview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Note note = notelist.get(position);
        holder.notetitle.setText(note.getTitle());
        holder.notetexts.setText(note.getText());
        String timeformat = DateFormat.getDateTimeInstance().format(note.DateandTime);
        holder.notedatentime.setText(timeformat);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("Delete");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Delete")){
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView notetitle;
        TextView notetexts;
        TextView notedatentime;

        public viewholder(@Nonnull View itemView) {
            super(itemView);
            notetitle = itemView.findViewById(R.id.TitletxtView);
            notetexts = itemView.findViewById(R.id.TexttxtView);
            notedatentime = itemView.findViewById(R.id.DateandTimetxtVw);
        }

    }
}
