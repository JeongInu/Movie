package org.movie.backend.repository;

import org.junit.jupiter.api.Test;
import org.movie.backend.entity.Member;
import org.movie.backend.entity.Movie;
import org.movie.backend.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  public void insertReviews(){

    IntStream.rangeClosed(1, 200).forEach(i -> {

      Long mno = (long)(Math.random() * 100) + 1;

      Long mid = ((long)(Math.random()*100)+1);
      Member member = Member.builder().mid(mid).build();

      Review movieReview = Review.builder()
              .member(member)
              .movie(Movie.builder().mno(mno).build())
              .grade((int)(Math.random()*5)+1)
              .text("이 영화에 대한 느낌..." + i)
              .build();

      reviewRepository.save(movieReview);

    });
  }

  @Test
  public void testGetMovieReviews(){

    Movie movie = Movie.builder().mno(92L).build();

    List<Review> result = reviewRepository.findByMovie(movie);

    result.forEach(movieReview -> {
      System.out.println(movieReview.getReviewnum());
      System.out.println("\t"+movieReview.getGrade());
      System.out.println("\t"+movieReview.getText());
      System.out.println("\t"+movieReview.getMember().getEmail());
      System.out.println("---------------------------------------");
    });

  }

}
