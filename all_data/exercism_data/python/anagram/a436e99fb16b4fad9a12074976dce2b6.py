def detect_anagrams(orig, candidates):
	orig = orig.lower()
	orig_count = count_chars(orig)
	solutions = []
	for word in candidates:
		if orig != word.lower() and orig_count == count_chars(word.lower()):
			solutions.append(word)
	return solutions

def count_chars(word):
	counter = {}
	for c in word:
		if c in counter:
			counter[c] += 1
		else:
			counter[c] = 1
	return counter
