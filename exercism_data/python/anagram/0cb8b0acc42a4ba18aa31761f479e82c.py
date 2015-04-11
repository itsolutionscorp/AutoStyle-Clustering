def detect_anagrams(word, seq):
	anagram = ""
	anagrams = []
	found = False
	for item in seq:
		if is_in(word.lower(), item.lower()):
			anagrams.append(item)
	return anagrams

def is_in(word, item):
	if word == item:
		return False
	if len(word) != len(item):
		return False
	for letter in word:
		if letter not in item:
			return False
		if word.count(letter) != item.count(letter):
			return False
	return True
