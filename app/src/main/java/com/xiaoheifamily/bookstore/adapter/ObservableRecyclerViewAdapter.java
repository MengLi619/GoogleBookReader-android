package com.xiaoheifamily.bookstore.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

public class ObservableRecyclerViewAdapter<T> extends RecyclerView.Adapter<ObservableRecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

        private final WeakReference<ObservableRecyclerViewAdapter<T>> adapterReference;

        public WeakReferenceOnListChangedCallback(ObservableRecyclerViewAdapter<T> adapter) {
            this.adapterReference = new WeakReference<>(adapter);
        }

        @Override
        public void onChanged(ObservableList sender) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        }
    }

    private final ObservableList<T> items;
    private final int itemLayout;
    private final int itemBindVariable;

    public ObservableRecyclerViewAdapter(@NonNull ObservableList<T> items, int itemLayout, int itemBindVariable) {

        this.items = items;
        this.itemLayout = itemLayout;
        this.itemBindVariable = itemBindVariable;
        this.items.addOnListChangedCallback(new WeakReferenceOnListChangedCallback<>(this));
    }

    @SuppressWarnings("unused")
    public ObservableList<T> getItems() {
        return items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), itemLayout, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final T item = items.get(position);

        viewHolder.getBinding().setVariable(itemBindVariable, item);
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}