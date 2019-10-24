package com.shuffle.fulltexo.service;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.shuffle.fulltexo.entity.Movie;
import com.shuffle.fulltexo.importer.Importer;
import com.shuffle.fulltexo.importer.Parser;
import com.shuffle.fulltexo.importer.RowValue;

@Service
public class ImportData {

	@PostConstruct
	private void loadData() {

		// lets wait a little bit while applicationContext is filled
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.schedule(() -> {
			InputStream movieList = this.getClass().getClassLoader().getResourceAsStream("movielist.csv");
			Parser<Movie> parser = new Parser<Movie>(Movie.class, movieList);
			List<RowValue> rowValues = parser.doParse();
			Importer<Movie> importer = new Importer<Movie>(Movie.class, rowValues);
			importer.doImport();
		}, 5, TimeUnit.SECONDS);
		scheduledExecutorService.shutdown();

	}
}
