def char_count(word):
	word = word.lower()
	charcount = {}
	for char in word:
		if char in charcount:
			charcount[char] += 1

		else:
			charcount[char] = 1

	charcount_sorted = []
	for key, value in charcount.items():
		charcount_sorted.append(key + str(value))

	return sorted(charcount_sorted)

def detect_anagrams(word, possibilities):
	anagrams = []
	for possibility in possibilities:
		if char_count(possibility) == char_count(word) and possibility.lower() != word.lower():
			anagrams.append(possibility)

	return anagrams
