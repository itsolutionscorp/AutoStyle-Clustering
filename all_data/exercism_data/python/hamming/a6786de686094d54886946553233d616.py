def distance(string1, string2):
	return sum(c != d for c, d in zip(string1, string2))
