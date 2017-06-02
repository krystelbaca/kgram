package github.com.krstelbaca.kgram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.facebook.rebound.ui.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.com.krstelbaca.kgram.view.SquaredImageView;

/**
 * @author krystel
 * @since 6/1/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final int ANIMATED_ITEMS_COUNT = 2;

    private Context context;
    private int lastAnimatedPosition = -1;
    private int itemsCount = 0;
    private OnFeedItemClickListener onFeedItemClickListener;

    public FeedAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }
        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(500)
                    .start();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        CellFeedViewHolder feedViewHolder = (CellFeedViewHolder) holder;
        if (position % 2 == 0) {
            feedViewHolder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_1);
            feedViewHolder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_1);
        } else {
            feedViewHolder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_2);
            feedViewHolder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_2);
        }
        feedViewHolder.ivFeedBottom.setOnClickListener(this);
        feedViewHolder.ivFeedBottom.setTag(position);
    }


    public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivFeedBottom) {
            if (onFeedItemClickListener != null) {
                onFeedItemClickListener.onCommentsClick(v, (Integer) v.getTag());
            }
        }
    }

    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }


    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivFeedCenter)
        SquaredImageView ivFeedCenter;

        @BindView(R.id.ivFeedBottom)
        ImageView ivFeedBottom;

        CellFeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnFeedItemClickListener {
        public void onCommentsClick(View v, int position);
    }
}
