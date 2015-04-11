#!/usr/bin/env python
 # -*- coding: utf-8 -*-
 
def detect_anagrams(word, word_list):
	if not isinstance(word, "".__class__) or not isinstance(word_list, [].__class__):
		raise ValueError("word must be a string and word_list a list of string")
	
	anagrams = []
	word_sorted = sorted_string(word)
	word_lowercased = word.lower()
	
	for w in word_list:
		if w.lower()!= word_lowercased and sorted_string(w) == word_sorted:
			anagrams.append(w)
	
	return anagrams
 
def sorted_string(s):
	return ''.join(sorted(s.lower()))
