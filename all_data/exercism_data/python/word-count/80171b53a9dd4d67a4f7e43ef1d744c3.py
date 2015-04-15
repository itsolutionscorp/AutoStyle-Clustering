#!/bin/python

def word_count(words):
	words = words.split()
	d = dict.fromkeys(words, 0)
	for word in words:
		d[word] += 1
	return d
