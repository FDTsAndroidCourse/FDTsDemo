/*
 * Copyright (c) 2020 fdt <frederic.dt.twh@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package onl.fdt.android.fdtsdemo.CyclerView;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import onl.fdt.android.fdtsdemo.CyclerView.CardView.CardActivity;
import onl.fdt.android.fdtsdemo.CyclerView.model.Message;
import onl.fdt.android.fdtsdemo.CyclerView.widget.CircleImageView;
import onl.fdt.android.fdtsdemo.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private final List<Message> messages;
    private final AppCompatActivity context;
    private Toast mToast;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final View view;
        private final TextView titleTextView;
        private final TextView descriptionTextView;
        private final TextView timeTextView;

        private int position = 0;
        private final CircleImageView circleImageView;
        public ViewHolder(View v) {
            super(v);
            this.view = v;
            this.titleTextView = v.findViewById(R.id.tv_title);
            this.descriptionTextView = v.findViewById(R.id.tv_description);
            this.timeTextView = v.findViewById(R.id.tv_time);
            this.circleImageView = v.findViewById(R.id.iv_avatar);
        }

        public void loadData(final Message m, int position) {
            this.titleTextView.setText(m.getTitle());
            this.descriptionTextView.setText(m.getDescription());
            this.timeTextView.setText(m.getTime());
            this.position = position;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (mToast != null) {
//                        mToast.cancel();
//                    }
//
                    String toastMessage = "Item #" + ViewHolder.this.position + " clicked.";
//                    mToast = Toast.makeText(DataAdapter.this.context, toastMessage, Toast.LENGTH_LONG);
//                    mToast.show();

                    Intent intent = new Intent(DataAdapter.this.context, CardActivity.class);
                    intent.putExtra(CardActivity.SHOW_MESSAGE_STRING_EXTRA_NAME, toastMessage);
                    DataAdapter.this.context.startActivity(intent);
                }
            });
            this.circleImageView.setImageDrawable(DataAdapter.this.context.getResources().getDrawable(m.iconDrawable()));
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DataAdapter(final List<Message> messages, AppCompatActivity context) {
        this.messages = messages;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.loadData(messages.get(position), position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return messages.size();
    }
}
