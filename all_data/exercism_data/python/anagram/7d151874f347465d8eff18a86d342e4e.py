def detect_anagrams(word, word_list):
	"""Checks a list for anagrams to a given word

	Keyword arguments:
	word -- word to compare anagarams with
	word_list -- list of words to check for anagarams

	Returns:
	list of words that are anagrams
	"""
	try:
		return [w for w in word_list if is_anagaram(word,w)]
	except ValueError:
		return []

def is_anagaram(word, anagram):
	"""Checks if two words are anagrams

	Returns:
	boolean
	"""
	# Sanity checks
	if word.strip() == '' or anagram.strip() == '':
		raise ValueError("Arguments cannot be empty")
	# Quickly discard obvious cases before using more computational heavy methods
	if len(word) != len(anagram):
		return False
	# A word is not an angaram to iteself
	elif word.lower() == anagram.lower():
		return False
	else:
		return sorted(word.lower()) == sorted(anagram.lower())
