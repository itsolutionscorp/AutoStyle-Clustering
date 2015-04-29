import string

def word_count(line):
	words = {}
	line = [x.strip(string.punctuation).lower() for x in line.split()]
	for w in line:
		if len(w) == 0:
			continue
		if words.has_key(w):
			words[w] += 1
		else:
			words[w] = 1

	return words
