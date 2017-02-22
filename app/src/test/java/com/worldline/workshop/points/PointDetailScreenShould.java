package com.worldline.workshop.points;

import com.worldline.workshop.core.Callback;
import com.worldline.workshop.core.Presenter;
import com.worldline.workshop.core.entity.ViewEntity;
import com.worldline.workshop.core.model.Model;
import com.worldline.workshop.core.view.ContentView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by PoL on 22/02/17.
 */

public class PointDetailScreenShould {

    PointDetailPresenter presenter;

    @Mock
    PointsModel modelMock;

    @Mock
    PointsListContentView contentViewMock;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new PointDetailPresenter(contentViewMock, modelMock);
    }

    @Test
    public void start_and_show_progress() {
        presenter.start();

        verify(contentViewMock).showProgress();
    }

    @Test
    public void start_and_get_data() {
        presenter.start();

        verify(modelMock).getData(any(Callback.class));
    }

    @Test
    public void show_error_and_hide_progress_on_ko() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback) invocation.getArguments()[0]).ko(null);
                return null;
            }
        }).when(modelMock).getData(any(Callback.class));

        presenter.start();

        verify(contentViewMock).showError();
        verify(contentViewMock).hideProgress();
    }

    @Test
    public void load_content_and_hide_progress_on_ok() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback) invocation.getArguments()[0]).ok(mock(PointsList.class));
                return null;
            }
        }).when(modelMock).getData(any(Callback.class));

        presenter.start();

        verify(contentViewMock).hideProgress();
        verify(contentViewMock).loadContent(any(PointsList.class));
    }

    @Test
    public void show_error_and_hide_progress_on_ok_with_invalid_response() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback) invocation.getArguments()[0]).ok(null);
                return null;
            }
        }).when(modelMock).getData(any(Callback.class));

        presenter.start();

        verify(contentViewMock).hideProgress();
        verify(contentViewMock).showError();
    }


    abstract class PointsList implements ViewEntity {
    }


    abstract class PointsListContentView implements ContentView<PointsList> {
    }


    abstract class PointsModel implements Model<PointsList> {
    }


    class PointDetailPresenter extends Presenter<PointsModel, PointsListContentView> {

        public PointDetailPresenter(PointsListContentView view, PointsModel model) {
            super(view, model);
        }

        @Override
        public void start() {

        }

        @Override
        public void stop() {

        }
    }

}
