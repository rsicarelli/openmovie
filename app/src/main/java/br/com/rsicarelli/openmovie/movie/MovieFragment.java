package br.com.rsicarelli.openmovie.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import br.com.rsicarelli.openmovie.R;
import br.com.rsicarelli.openmovie.api.MovieClient;
import br.com.rsicarelli.openmovie.data.MovieResponse;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class MovieFragment extends Fragment implements MovieContract.View {
    public static final String ARG_MOVIE_ID = "arg_movie_id";

    @Bind(R.id.progress_view)
    CircularProgressView progressView;

    @Bind(R.id.year)
    TextView viewYear;

    @Bind(R.id.runtime)
    TextView viewRuntime;

    @Bind(R.id.genre)
    TextView viewGenre;

    @Bind(R.id.plot)
    TextView viewPlot;

    private View viewRoot;

    public MovieFragment() {
        // Requires empty public constructor
    }

    public static MovieFragment newInstance(String movieId) {
        MovieFragment fragment = new MovieFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_MOVIE_ID, movieId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        String movieId = getArguments().getString(ARG_MOVIE_ID);

        if (!TextUtils.isEmpty(movieId)) {
            MovieContract.UserInteractions mUserInteractionsListener = new MoviePresenter(new MovieClient(), this);
            mUserInteractionsListener.doSearch(movieId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.bind(this, viewRoot);

        return viewRoot;
    }

    @Override
    public void showResults(MovieResponse result) {
        viewYear.setText(result.year);
        viewGenre.setText(result.genre);
        viewRuntime.setText(result.runtime);
        viewPlot.setText(result.plot);
    }

    @Override
    public void setProgressIndicator(boolean active) {
        progressView.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(viewRoot, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

}
