''' anagram.py
	created 1 Oct 2014
	by @jestuber '''

def detect_anagrams(word,list):
	matches = []

	for candidate in list:
		matches.append(candidate)
		if candidate.lower() == word.lower():
			matches.remove(candidate)
		elif len(word) != len(candidate):
			matches.remove(candidate)
		else:
			test_cand = candidate.lower()
			for c in word:
				if c.lower() not in test_cand:
					matches.remove(candidate)
					break
				else:
					test_cand = test_cand.replace(c,"",1)
	return matches
