package com.shuffle.fulltexo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shuffle.fulltexo.entity.Movie;
import com.shuffle.fulltexo.repository.view.StudioAndWinCount;
import com.shuffle.fulltexo.repository.view.WinIntervalForProducers;
import com.shuffle.fulltexo.repository.view.YearAndWinnerCount;

@Repository
public interface MovieRepository extends GenericRepository<Movie> {

	@Query(value = "SELECT NEW com.shuffle.fulltexo.repository.view.WinIntervalForProducers(p.name, MIN(m.year), MAX(m.year), MAX(m.year) - MIN(m.year)) FROM Movie m INNER JOIN m.producers p WHERE m.winner=true GROUP BY p.name HAVING COUNT(m.title) > 1 ORDER BY MAX(m.year) - MIN(m.year) DESC, p.name ASC")
	List<WinIntervalForProducers> findMaxWinIntervalForProducers(Pageable pageable);
	
	@Query(value = "SELECT NEW com.shuffle.fulltexo.repository.view.WinIntervalForProducers(p.name, MIN(m.year), MAX(m.year), MAX(m.year) - MIN(m.year)) FROM Movie m INNER JOIN m.producers p WHERE m.winner=true GROUP BY p.name HAVING COUNT(m.title) > 1 ORDER BY MAX(m.year) - MIN(m.year) ASC, p.name ASC")
	List<WinIntervalForProducers> findMinWinIntervalForProducers(Pageable pageable);

	@Query(value = "SELECT NEW com.shuffle.fulltexo.repository.view.YearAndWinnerCount(m.year, COUNT(m.year)) FROM Movie m WHERE m.winner = true GROUP BY m.year HAVING COUNT(m.year) > 1 ORDER BY COUNT(m.year) DESC, m.year ASC")
	List<YearAndWinnerCount> findYearsWithMultipleWinners();
	
	@Query(value = "SELECT NEW com.shuffle.fulltexo.repository.view.StudioAndWinCount(s.name, COUNT(s.name)) FROM Movie m INNER JOIN m.studios s WHERE m.winner = true GROUP BY s.name ORDER BY COUNT(s.name) DESC, s.name ASC")
	List<StudioAndWinCount> findStudiosWithWinCount(Pageable pageable);
}
