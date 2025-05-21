package org.movie.backend.repository;

import org.movie.backend.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  @Query("SELECT m, mi, AVG(COALESCE(r.grade, 0)), COUNT(DISTINCT r) " +
          "FROM Movie m " +
          "LEFT JOIN MovieImage mi on mi.movie = m " +
          "LEFT JOIN Review r ON r.movie = m " +
          "GROUP BY m, mi")
  Page<Object[]> getListPage(Pageable pageable);

  @Query("SELECT m, mi, AVG(COALESCE(r.grade, 0)), COUNT(DISTINCT r) " +
          "FROM Movie m " +
          "LEFT JOIN MovieImage mi on mi.movie = m " +
          "LEFT JOIN Review r ON r.movie = m " +
          "WHERE m.mno = :mno " +
          "GROUP BY m, mi")
  List<Object[]> getMovieWithAll(Long mno);

}
