from collections import Counter

def detect_anagrams(word, candidates):

	word = word.lower()
	word_cnt = Counter(word)

	return [
		candidate
		for candidate in candidates
		if candidate.lower() != word and
			word_cnt == Counter(candidate.lower())
	]
