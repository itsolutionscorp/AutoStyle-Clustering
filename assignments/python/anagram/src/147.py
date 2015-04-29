from collections import Counter
def detect_anagrams(word, words):
	wordCounter = Counter(word.lower())
	result = []
	for ana in words:
		anaCounter = Counter(ana.lower())
		if anaCounter == wordCounter and ana.lower() != word.lower():
			result.append(ana)
	return result
