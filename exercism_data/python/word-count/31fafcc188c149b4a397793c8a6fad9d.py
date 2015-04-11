#!/usr/bin/python

def word_count(phrase):
	dict = {}
	for i in phrase.split():
		if i in dict:
			dict[i] += 1
		else:
			dict[i] = 1
	return dict
