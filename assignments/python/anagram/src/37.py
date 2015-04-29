''' anagram.py
	created 1 Oct 2014
	by @jestuber '''
def detect_anagrams(word,list):
	matches = []
	for candidate in list:
		if candidate.lower() == word.lower():
			continue
		elif len(word) != len(candidate):
			continue
		else:
			matches.append(candidate)
			test_cand = candidate
			for c in word:
				if c.lower() not in test_cand.lower():
					matches.remove(candidate)
					break
				else:
					test_cand = test_cand.replace(c,"",1)
	return matches
