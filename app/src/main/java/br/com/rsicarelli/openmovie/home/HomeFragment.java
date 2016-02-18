package br.com.rsicarelli.openmovie.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import br.com.rsicarelli.openmovie.R;
import br.com.rsicarelli.openmovie.adapters.MoviesAdapter;
import br.com.rsicarelli.openmovie.api.MovieClient;
import br.com.rsicarelli.openmovie.bus.RxBusManager;
import br.com.rsicarelli.openmovie.bus.events.SearchEvent;
import br.com.rsicarelli.openmovie.data.Movie;
import br.com.rsicarelli.openmovie.movie.MovieActivity;
import br.com.rsicarelli.openmovie.widget.VerticalRecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class HomeFragment extends Fragment implements HomeContract.View {

    @Bind(R.id.movie_list)
    VerticalRecyclerView movieList;

    @Bind(R.id.progress_view)
    CircularProgressView progressView;

    OnMovieClickListener itemListener = this::navigateToMovieDetail;

    private MoviesAdapter mAdapter;

    private HomeContract.UserInteractions mUserInteractionsListener;
    private View mRoot;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public HomeFragment() {
        // Requires empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        mUserInteractionsListener = new HomePresenter(new MovieClient(), this);
        mUserInteractionsListener.doSearch("Batman");

        initBus();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, mRoot);

        return mRoot;
    }

    @Override
    public void showResults(List<Movie> result) {
        if (mAdapter == null) {
            mAdapter = new MoviesAdapter(result, itemListener);
            movieList.setAdapter(mAdapter);
        } else {
            mAdapter.replaceData(result);
        }
    }

    @Override
    public void setProgressIndicator(boolean active) {
        progressView.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(mRoot, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    private void initBus() {
        RxBusManager.getInstance()
                .toObservable()
                .ofType(SearchEvent.class)
                .cast(SearchEvent.class)
                .subscribe(searchEvent -> {
                    if (!TextUtils.isEmpty(searchEvent.query)
                            && searchEvent.query.length() > 1) {
                        mUserInteractionsListener.doSearch(searchEvent.query);
                    }
                });
    }

    private void navigateToMovieDetail(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra(MovieActivity.EXTRA_MOVIE_ID, movie.id);
        intent.putExtra(MovieActivity.EXTRA_MOVIE_TITLE, movie.title);
        intent.putExtra(MovieActivity.EXTRA_MOVIE_AVATAR, movie.getPoster());
        startActivity(intent);
    }
}
