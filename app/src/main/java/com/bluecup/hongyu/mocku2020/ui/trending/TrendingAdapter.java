package com.bluecup.hongyu.mocku2020.ui.trending;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bluecup.hongyu.mocku2020.R;
import com.bluecup.hongyu.mocku2020.data.api.module.Repository;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import rx.functions.Action1;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午4:05
 */
public final class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder>
        implements Action1<List<Repository>> {


    private final Picasso picasso;
    private final RepositoryClickListener repositoryClickListener;

    private List<Repository> repositories = Collections.emptyList();

    public TrendingAdapter(Picasso picasso, RepositoryClickListener repositoryClickListener) {
        this.picasso = picasso;
        this.repositoryClickListener = repositoryClickListener;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrendingItemView view = (TrendingItemView) LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_view_repository
                , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    @Override
    public void call(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    public interface RepositoryClickListener {
        void onRepositoryClick(Repository repository);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        private final TrendingItemView trendingItemView;

        public ViewHolder(TrendingItemView itemView) {
            super(itemView);
            this.trendingItemView = itemView;
            trendingItemView.setOnClickListener(v -> {

            });
        }

        public void bindTo(Repository repository) {
            trendingItemView.bindTo(repository, picasso);
        }
    }
}
