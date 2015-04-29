def word_count(string):
	strings = string.split()
	return {w: strings.count(w) for w in set(strings)}
