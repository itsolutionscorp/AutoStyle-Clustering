# O(nW) time complexity, n = size of word, W = # words.
# RESTRICTIONS: Can't be the same word, case insensitive.
def detect_anagrams(word, potentials):
	word = word.lower()
	dct = {}
	for char in word:
		dct[char] = 1 if char not in dct else dct[char] + 1

	results = []
	for potential in potentials:
		if word == potential.lower():
			continue
		h = {}
		for char in potential.lower():
			h[char] = 1 if char not in h else h[char] + 1
		if dct == h:
			results.append(potential)
	return results
