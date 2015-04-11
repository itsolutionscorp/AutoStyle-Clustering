# anagram.py

# return the sublist of words that are anagrams of the source word
def detect_anagrams(source, test_list):
	anagram_list = [word for word in test_list if is_anagram(source,word)]

	return anagram_list

# determine if one word is an anagram of another
def is_anagram(source,test):
	# anagrams are case insensitive
	source = source.lower()
	test = test.lower()

	# anagrams must contain the same number of letters,
	# and a word is not an anagram of itself
	if (len(source) != len(test) or source == test):
		return False

	# split the strings into characters and order them for easy comparison
	source_chars = [c for c in source]
	test_chars = [c for c in test]

	source_chars.sort()
	test_chars.sort()

	return source_chars == test_chars
