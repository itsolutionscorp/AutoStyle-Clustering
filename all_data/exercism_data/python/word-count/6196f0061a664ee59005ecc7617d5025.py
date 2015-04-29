#!/usr/bin/python
# -*- coding: utf-8 -*-
def word_count(input_str):
	d = dict()
	words = input_str.split()
	for word in words:
		if word in d:
			d[word] += 1
		else:
			d[word] = 1
	return d
