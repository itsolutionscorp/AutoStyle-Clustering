def detect_anagrams(given_word, word_list):
	# Force given word to lowercase so we can do case-insensitive comparisons.
	given_word = given_word.lower()
	
	# Get the characters of the given word as a sorted list for comparison.
	given_word_chars = sorted(list(given_word))
	
	# Variable to hold our resulting list of anagrams.
	anagram_list = []
	
	# Loop through the word list.
	# Compare the sorted list of characters of the given word
	# with the sorted list of characters of the current word.
	# If they match, add the current word to the anagram list.
	# Take into account that a word is not an anagram of itself.
	for current_word in word_list:
		if sorted(list(current_word.lower())) == given_word_chars and\
		    current_word.lower() != given_word:
			anagram_list.append(current_word)
	
	return anagram_list
