package com.shabaka.shabakautils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class AutoCompleteAdapter extends ArrayAdapter<IAutoAdapterItem> {


    private int layoutContainerId, nameContainerId, descriptionContainerId, imageContainerId;

    public AutoCompleteAdapter(Context context, int layoutContainerId,
                               int nameContainerId, int descriptionContainerId,
                               int imageContainerId, List<IAutoAdapterItem> objects) {
        super(context, 0, objects);
        this.layoutContainerId = layoutContainerId;
        this.nameContainerId = nameContainerId;
        this.descriptionContainerId = descriptionContainerId;
        this.imageContainerId = imageContainerId;

        this.items = new ArrayList<>(objects);
    }

    public void setItemsList(List<IAutoAdapterItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }
    private List<IAutoAdapterItem> items;

    @Override
    public Filter getFilter() {
        return filter;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    layoutContainerId, parent, false
            );
        }
        TextView name = convertView.findViewById(nameContainerId);
        TextView description = convertView.findViewById(descriptionContainerId);


        IAutoAdapterItem item = getItem(position);
        if(item != null){
            name.setText(item.getName());
            description.setText(item.getDescription());

            if(imageContainerId != 0){
                ImageView image = convertView.findViewById(imageContainerId);
                Glide.with(image.getContext()).load(item.getImageRessource())
                        .into(image);
            }


        }

        return convertView;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<IAutoAdapterItem> suggestions = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                suggestions.addAll(items);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (IAutoAdapterItem item : items){
                    if(item.getDescription().toLowerCase().contains(filterPattern)) suggestions.add(item);
                    if(item.getMetadata().toLowerCase().contains(filterPattern)) suggestions.add(item);
                    if(item.getName().toLowerCase().contains(filterPattern)) suggestions.add(item);
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List)filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((IAutoAdapterItem) resultValue).getName();
        }
    };
}