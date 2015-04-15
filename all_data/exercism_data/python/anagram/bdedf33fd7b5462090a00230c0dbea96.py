from itertools import permutations

def detect_anagrams(original_word, words_to_check):
	original_word = original_word.lower()
	anagrams_of_word = [''.join(p) for p in permutations(original_word)]
	detected = list()
	for word in words_to_check:
		if word.lower() in anagrams_of_word and not word.lower() == original_word:
			detected.append(word)
	return detected
