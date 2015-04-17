#!/usr/bin/env python
from collections import Counter
def detect_anagrams(word, anagram_lst):
	return_list = []
	for anagram in anagram_lst:
		if Counter(word.lower()) == Counter(anagram.lower()) and word.lower() != anagram.lower():
			return_list.append(anagram)
	return return_list
