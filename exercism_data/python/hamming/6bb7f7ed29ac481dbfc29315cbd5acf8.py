def hamming(string1, string2):
	length_difference = abs(len(string1) - len(string2))
	return sum(char1 != char2 for char1, char2 in zip(string1, string2)) + length_difference
