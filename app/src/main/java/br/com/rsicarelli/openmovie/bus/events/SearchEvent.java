package br.com.rsicarelli.openmovie.bus.events;

/**
 * Created by rodrigosicarelli on 1/20/16.
 */
public class SearchEvent extends AbstractEvent {
    public final String query;

    public SearchEvent(String query) {
        this.query = query;
    }
}
