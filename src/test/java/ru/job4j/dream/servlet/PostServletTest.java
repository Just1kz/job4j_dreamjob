package ru.job4j.dream.servlet;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {

    @Test
    public void whenDoPost() throws ServletException, IOException {
        Store store = MemStore.instOf();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);
        PowerMockito.when(req.getParameter("id")).thenReturn("0");
        PowerMockito.when(req.getParameter("name")).thenReturn("Junior");
        PowerMockito.when(req.getParameter("description")).thenReturn("SberBank");
        new PostServlet().doPost(req, resp);

        Post result = store.findAllPosts().iterator().next();
        Assert.assertThat(result.getName(), Is.is("Junior"));
        Assert.assertThat(result.getDescription(), Is.is("SberBank"));
    }

    @Test
    public void whenDoGet() throws ServletException, IOException {
        Store store = MemStore.instOf();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        PowerMockito.mockStatic(PsqlStore.class);
        when(PsqlStore.instOf()).thenReturn(store);
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher(any())).thenReturn(dispatcher);
        new PostServlet().doGet(req, resp);

        Assert.assertThat(store.size(), Is.is(0));
    }

}