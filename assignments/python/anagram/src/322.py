def detect_anagrams(word, possibleWords):
	"""Select the correct anagrams for a word from a list."""
	possibleLetters = []
	anagrams = []
	word = word.lower()													# Control for case
	for possible in possibleWords:
		if len(possible) == len(word):									# Check for equality and length
			if word != possible.lower():
				possibleLetters.append(list(possible.lower()))			# Create a parallel list of the possible words' letters,
				anagrams.append(possible)								# will drop as they match the original word to test equality
	for x in range(len(anagrams) - 1, -1, -1):							# Loop through the list of possible words backwards, so
		match = True													# that popping them doesn't break the other's position
		for letter in word:
			try:
				possibleLetters[x].pop(possibleLetters[x].index(letter)) # Check that the letter exists and drop it (once)
			except:
				match = False
				break
		if not match or len(possibleLetters[x]) != 0: 					# Drop the word if there's a mismatched or extra letter
			anagrams.pop(x)
	return anagrams
# OTHER CONSIDERATIONS:
	# Wasn't initially aware that phrase.split() split on white
	# space. Updated per amykhar for 12X performance boost
		# possibleLetters = [list(possible.lower()) for possible in possibleWords]
# Author's Notes on Coding Conventions:
	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,E261,W293,E701
		# max-line-length = 120
# # Anagram
# Write a program that, given a word and a list of possible anagrams, selects the correct sublist.
# Given `"listen"` and a list of candidates like `"enlists" "google"
# "inlets" "banana"` the program should return a list containing
# `"inlets"`.
