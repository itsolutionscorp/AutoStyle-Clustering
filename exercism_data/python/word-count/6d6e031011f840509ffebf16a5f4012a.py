# -*- coding: utf-8 -*-

def word_count(string):

	words = string.split()

	output = {}

	for word in words:
		output[word] = words.count(word)

	return output
