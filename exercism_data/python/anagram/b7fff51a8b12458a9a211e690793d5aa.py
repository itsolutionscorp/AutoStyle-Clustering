#!/usr/bin/env python

def detect_anagrams(s, words):
	matches = []
	for word in words:
		if len(word) != len(s) or word.lower() == s.lower():
			continue
		for letter in word:
			if letter.lower() not in s.lower():
				break
			else:
				if word.index(letter) == len(word)-1:
					matches.append(word)
	return matches
