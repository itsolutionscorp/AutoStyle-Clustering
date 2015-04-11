def distance(strand1, strand2):
	return sum([not(letter1==letter2) for letter1, letter2 in zip(strand1, strand2)])
