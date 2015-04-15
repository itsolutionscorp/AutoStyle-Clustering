''' anagram.py
	created 1 Oct 2014
	by @jestuber '''

def detect_anagrams(word,list):
	matches = []

	for candidate in list:
		c = candidate.lower()
		w = word.lower()
		if c == w:
			continue
		elif len(c) != len(w):
			continue
		elif sorted(c) == sorted(w):
			matches.append(candidate)

	return matches
