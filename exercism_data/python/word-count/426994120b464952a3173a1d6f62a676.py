# -*- coding: utf-8 -*-


def word_count(input):
	words = {}
	for word in input.split():
		if word in words:
			words[word] += 1
		else:
			words[word] = 1

	return words
