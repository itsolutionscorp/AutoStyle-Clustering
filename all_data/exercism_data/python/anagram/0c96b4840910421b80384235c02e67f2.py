def get_word_value(word):
	value = 0
	for char in list(word.upper()):
		value += ord(char)
	
	return value

def detect_anagrams(word, candidates):
	word_value = get_word_value(word)
	matched_list = []
	
	for candidate in candidates:
		if candidate == word:
			break
			
		if word_value == get_word_value(candidate):
			matched_list.append(candidate)
			
	return matched_list
