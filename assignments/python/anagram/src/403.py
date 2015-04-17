#!/usr/bin/python -tt
	
def detect_anagrams(inStr,lst):
	
	sortedStr = sorted(inStr.lower())
	out = []
	
	for word in lst:
		if (sortedStr == sorted(word.lower()) and inStr.lower() != word.lower()):
			out.append(word)
	return out
