package br.com.rsicarelli.openmovie.movie;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import br.com.rsicarelli.openmovie.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    public final static String EXTRA_MOVIE_ID = "movie_id";
    public final static String EXTRA_MOVIE_TITLE = "title";
    public final static String EXTRA_MOVIE_AVATAR = "avatar";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.movie_avatar)
    SimpleDraweeView viewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (null == savedInstanceState) {
            if (getIntent().hasExtra(EXTRA_MOVIE_ID)) {
                String movieId = getIntent().getStringExtra(EXTRA_MOVIE_ID);
                initFragment(MovieFragment.newInstance(movieId));
            }
            if (getIntent().hasExtra(EXTRA_MOVIE_TITLE)) {
                String movieTitle = getIntent().getStringExtra(EXTRA_MOVIE_TITLE);
                toolbar.setTitle(movieTitle);
                getSupportActionBar().setTitle(movieTitle);
            }
            if (getIntent().hasExtra(EXTRA_MOVIE_AVATAR)) {
                String movieAvatar = getIntent().getStringExtra(EXTRA_MOVIE_AVATAR);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(movieAvatar))
                        .setProgressiveRenderingEnabled(true)
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .build();

                viewAvatar.setController(controller);
            }
        }
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
