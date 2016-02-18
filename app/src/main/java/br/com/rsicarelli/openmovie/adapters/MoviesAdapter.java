package br.com.rsicarelli.openmovie.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import br.com.rsicarelli.openmovie.R;
import br.com.rsicarelli.openmovie.data.Movie;
import br.com.rsicarelli.openmovie.home.HomeFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.common.internal.Preconditions.checkNotNull;

/**
 * Created by rodrigosicarelli on 1/19/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies;
    private HomeFragment.OnMovieClickListener movieClickListener;

    public MoviesAdapter(List<Movie> movies, HomeFragment.OnMovieClickListener movieClickListener) {
        setList(movies);
        this.movieClickListener = movieClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(movieView, movieClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movie = movies.get(position);

        viewHolder.title.setText(movie.title);
        viewHolder.year.setText(movie.year);

        Uri uri = Uri.parse(movie.getPoster());

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();

        viewHolder.moviePoster.setController(controller);
    }

    public void replaceData(List<Movie> movies) {
        setList(movies);
        notifyDataSetChanged();
    }

    private void setList(List<Movie> movies) {
        this.movies = checkNotNull(movies);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        public TextView title;

        @Bind(R.id.year)
        public TextView year;

        @Bind(R.id.movie_poster)
        public SimpleDraweeView moviePoster;

        private HomeFragment.OnMovieClickListener movieClickListener;

        public ViewHolder(View itemView, HomeFragment.OnMovieClickListener listener) {
            super(itemView);
            movieClickListener = listener;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.movie_detail)
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = getItem(position);
            movieClickListener.onMovieClick(movie);
        }
    }
}