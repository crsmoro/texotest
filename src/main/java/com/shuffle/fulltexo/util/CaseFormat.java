package com.shuffle.fulltexo.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CaseFormat {
	/**
	 * Hyphenated variable naming convention, e.g., "lower-hyphen".
	 */
	LOWER_HYPHEN(Pattern.compile("[-]"), "-"),
	/**
	 * C++ variable naming convention, e.g., "lower_underscore".
	 */
	LOWER_UNDERSCORE(Pattern.compile("[_]"), "_"),
	/**
	 * Java variable naming convention, e.g., "lowerCamel".
	 */
	LOWER_CAMEL(Pattern.compile("[A-Z]"), ""),
	/**
	 * Java and C++ class naming convention, e.g., "UpperCamel".
	 */
	UPPER_CAMEL(Pattern.compile("[A-Z]"), ""),
	/**
	 * Java and C++ constant naming convention, e.g., "UPPER_UNDERSCORE".
	 */
	UPPER_UNDERSCORE(Pattern.compile("[_]"), "_");
	private final Pattern wordBoundary;
	private final String wordSeparator;

	private CaseFormat(Pattern wordBoundary, String wordSeparator) {
		this.wordBoundary = wordBoundary;
		this.wordSeparator = wordSeparator;
	}

	/**
	 * Converts the specified {@code String s} from this format to the specified
	 * {@code format}. A "best effort" approach is taken; if {@code s} does not
	 * conform to the assumed format, then the behavior of this method is undefined
	 * but we make a reasonable effort at converting anyway.
	 */
	public String to(CaseFormat format, String s) {
		if (format == null) {
			throw new NullPointerException();
		}
		if (s == null) {
			throw new NullPointerException();
		}
		/* optimize case where no conversion is required */
		if (format == this) {
			return s;
		}
		/* optimize cases where no camel conversion is required */
		switch (this) {
		case LOWER_HYPHEN:
			switch (format) {
			case LOWER_UNDERSCORE:
				return s.replace("-", "_");
			case UPPER_UNDERSCORE:
				return s.replace("-", "_").toUpperCase(Locale.US);
			default:
				break;
			}
			break;
		case LOWER_UNDERSCORE:
			switch (format) {
			case LOWER_HYPHEN:
				return s.replace("_", "-");
			case UPPER_UNDERSCORE:
				return s.toUpperCase(Locale.US);
			default:
				break;
			}
			break;
		case UPPER_UNDERSCORE:
			switch (format) {
			case LOWER_HYPHEN:
				return s.replace("_", "-").toLowerCase(Locale.US);
			case LOWER_UNDERSCORE:
				return s.toLowerCase(Locale.US);
			default:
				break;
			}
			break;
		default:
			break;
		}
		/* otherwise, deal with camel conversion */
		StringBuilder out = null;
		int i = 0;
		for (Matcher matcher = wordBoundary.matcher(s); matcher.find();) {
			int j = matcher.start();
			if (i == 0) {
				/* include some extra space for separators */
				out = new StringBuilder(s.length() + 4 * wordSeparator.length());
				out.append(format.normalizeFirstWord(s.substring(i, j)));
			} else {
				out.append(format.normalizeWord(s.substring(i, j)));
			}
			out.append(format.wordSeparator);
			i = j + wordSeparator.length();
		}
		if (i == 0) {
			return format.normalizeFirstWord(s);
		}
		out.append(format.normalizeWord(s.substring(i)));
		return out.toString();
	}

	private String normalizeFirstWord(String word) {
		switch (this) {
		case LOWER_CAMEL:
			return word.toLowerCase(Locale.US);
		default:
			return normalizeWord(word);
		}
	}

	private String normalizeWord(String word) {
		switch (this) {
		case LOWER_HYPHEN:
			return word.toLowerCase(Locale.US);
		case LOWER_UNDERSCORE:
			return word.toLowerCase(Locale.US);
		case LOWER_CAMEL:
			return toTitleCase(word);
		case UPPER_CAMEL:
			return toTitleCase(word);
		case UPPER_UNDERSCORE:
			return word.toUpperCase(Locale.US);
		}
		throw new RuntimeException("unknown case: " + this);
	}

	private static String toTitleCase(String word) {
		return (word.length() < 2) ? word.toUpperCase(Locale.US) : (Character.toTitleCase(word.charAt(0)) + word.substring(1).toLowerCase(Locale.US));
	}
}