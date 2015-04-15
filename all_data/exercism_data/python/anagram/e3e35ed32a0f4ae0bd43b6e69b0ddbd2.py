from collections import Counter

def detect_anagrams(words, anagrams):
	possibleanagrams = []
	lettercount = Counter(words.lower())
	for word in anagrams:
		if Counter(word.lower()) == lettercount and word.lower() != words.lower():
			possibleanagrams.append(word)
	return possibleanagrams
