package org.movie.backend.repository;

import org.movie.backend.entity.Member;
import org.movie.backend.entity.Movie;
import org.movie.backend.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
  List<Review> findByMovie(Movie movie);

  @Modifying
  @Query("DELETE FROM Review r WHERE r.member = :member")
  void deleteByMember(Member member);


}
