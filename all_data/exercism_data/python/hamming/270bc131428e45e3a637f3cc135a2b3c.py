def distance(string1, string2):
	return sum(char1 != char2 for char1, char2 in zip(string1, string2))
