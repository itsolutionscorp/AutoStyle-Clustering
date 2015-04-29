def detect_anagrams(word, grams):
	valid = []
	word_letters = count_letters(word)
	for gram in grams:
		gram_letters = count_letters(gram)
		valid.append(gram)
		for c in gram_letters:
			if gram_letters[c] != word_letters.get(c, 0) or word.lower() in gram.lower() or len(word) != len(gram):
				valid.remove(gram)
				break

	return valid

def count_letters(word):
	letter_count = {}
	for c in word.lower():
		letter_count[c] = letter_count.get(c, 0) + 1

	return letter_count
