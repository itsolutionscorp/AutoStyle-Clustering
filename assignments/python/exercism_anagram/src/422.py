def detect_anagrams(word, anagrams):
	result = []
	for item in anagrams:
		word = word.lower()
		if (word == item.lower()) : continue
		if (len(word) == len(item)):
			temp_word = word
			for letter in item.lower():
				if (letter in temp_word):
					index = temp_word.index(letter)
					temp_word = temp_word[0:index] + temp_word[index+1:]
			if (len(temp_word) == 0): result.append(item)
	return result
