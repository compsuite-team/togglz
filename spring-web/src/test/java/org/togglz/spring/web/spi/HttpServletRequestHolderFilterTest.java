package org.togglz.spring.web.spi;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.togglz.servlet.util.HttpServletRequestHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

public class HttpServletRequestHolderFilterTest {

  private HttpServletRequestHolderFilter filter;
  private FilterChain filterChain;
  private HttpServletRequest request;
  private HttpServletResponse response;

  @Before
  public void setup() {
    filter = new HttpServletRequestHolderFilter();
    request = mock(HttpServletRequest.class);
    filterChain = mock(FilterChain.class);
    response = mock(HttpServletResponse.class);
    spy(HttpServletRequestHolder.class);
  }

//  @Test
//  public void shouldCallFilterChain() throws ServletException, IOException {
//    filter.doFilter(request, response, filterChain);
//    verify(filterChain, times(1)).doFilter(request, response);
//  }

//  @Test
//  public void shouldBindCorrectRequest() throws ServletException, IOException {
//    filter.doFilter(request, response, filterChain);
//    verifyStatic(HttpServletRequestHolder.class, times(1));
//    HttpServletRequestHolder.bind(any());
//  }

//  @Test
//  public void shouldReleaseRequest() throws ServletException, IOException {
//    filter.doFilter(request, response, filterChain);
//    verifyStatic(HttpServletRequestHolder.class, times(1));
//    HttpServletRequestHolder.release();
//  }

//  @Test
//  public void shouldReleaseRequestOnExceptionWhileBinding() {
//    doThrow(new RuntimeException("boooom")).when(HttpServletRequestHolder.class);
//    HttpServletRequestHolder.bind(any());
//    assertThrows(RuntimeException.class, () -> filter.doFilter(request, response, filterChain));
//    verifyStatic(HttpServletRequestHolder.class, times(1));
//    HttpServletRequestHolder.release();
//  }

  @Test
  public void shouldReleaseRequestOnExceptionWhileFiltering() throws ServletException, IOException {
    doThrow(new RuntimeException("boooom")).when(filterChain).doFilter(any(), any());
    assertThrows(RuntimeException.class, () -> filter.doFilter(request, response, filterChain));
    verifyStatic(HttpServletRequestHolder.class, times(1));
    HttpServletRequestHolder.release();
  }
}
