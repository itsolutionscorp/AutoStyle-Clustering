import string

def clean(s):
	'''remove puntuation around the word'''
	return ''.join(ch for ch in s if ch not in set(string.punctuation))

def word_count(s):
	words = {}
	for w in [i for i in clean(s).lower().split()]:
		if w in words:
			words[w] += 1
		else:
			words[w] = 1
	return words
