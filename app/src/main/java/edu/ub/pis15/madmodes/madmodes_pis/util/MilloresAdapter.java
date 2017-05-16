package edu.ub.pis15.madmodes.madmodes_pis.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.List;

import edu.ub.pis15.madmodes.madmodes_pis.R;

/**
 * Created by mcalvico7.alumnes on 02/05/17.
 */

public class MilloresAdapter extends RecyclerView.Adapter<MilloresAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Millora> millores;
    // Store the context for easy access
    private Context mContext;
    // Define listener member variable
    private static OnItemClickListener listener;


    // Pass in the contact array into the constructor
    public MilloresAdapter(Context context, List<Millora> m) {
        millores = m;
        mContext = context;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView punts;
        public TextView valor;
        public ImageButton messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });

            punts = (TextView) itemView.findViewById(R.id.punts);
            valor = (TextView) itemView.findViewById(R.id.percent);
            messageButton = (ImageButton) itemView.findViewById(R.id.fotoo);
        }
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return millores.size();
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public MilloresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_millora, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MilloresAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Millora millora = millores.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.punts;
        textView.setText("Cost:\n"+millora.getCost());
        TextView textView2 = viewHolder.valor;
        ImageButton button = viewHolder.messageButton;

        switch (millora.getTipus()){
            case 1://defensa
                textView2.setText(" Health gains:\n "+millora.getValor()+" points");
                button.setBackgroundResource(R.drawable.shield);
                break;
            case 2: // atac
                textView2.setText(" Attack gains \n "+millora.getValor()+" damage");
                button.setBackgroundResource(R.drawable.swords);


        }

    }

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}