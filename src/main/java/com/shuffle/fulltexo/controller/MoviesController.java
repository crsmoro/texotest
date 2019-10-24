package com.shuffle.fulltexo.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shuffle.fulltexo.repository.MovieRepository;
import com.shuffle.fulltexo.service.MovieService;
import com.shuffle.fulltexo.util.CaseFormat;

@RestController
@RequestMapping(value = "api/movies")
public class MoviesController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping(params = { "projection" })
	public ResponseEntity<?> getProjection(@RequestParam() String projection) {
		
		try {
			return ResponseEntity.ok((movieService.getClass().getDeclaredMethod("get" + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, projection)).invoke(movieService)));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping(method = {RequestMethod.GET})
	public ResponseEntity<?> listMovies(@RequestParam Map<String,String> allParams)
	{
		return ResponseEntity.ok(movieRepository.findAll((entity, cq, cb) -> {
			
			List<Predicate> preds = new ArrayList<>();
			for (String key : allParams.keySet())
			{
				if (!key.equalsIgnoreCase("page") && !key.equalsIgnoreCase("size"))
				{
					try {
						Path<Object> path = entity.get(key);
						Method valueOfMethod = path.getJavaType().getDeclaredMethod("valueOf", String.class);
						preds.add(cb.equal(entity.get(key), valueOfMethod.invoke(path, allParams.get(key))));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						preds.add(cb.equal(entity.get(key), allParams.get(key)));
					}
				}
			}
			return cb.and(preds.toArray(new Predicate[preds.size()]));
			
		}, PageRequest.of(Optional.of(allParams.getOrDefault("page", "0")).map(s -> { try { return Integer.valueOf(s); } catch (NumberFormatException e) {
			return 0;
		}}).get(), Optional.of(allParams.getOrDefault("size", "15")).map(s -> { try { return Integer.valueOf(s); } catch (NumberFormatException e) {
			return 0;
		}}).get())));
	}
}
