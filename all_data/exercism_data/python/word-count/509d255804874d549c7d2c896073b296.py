import string

def word_count(phrase):
	phrase = phrase.translate(str.maketrans('', '', string.punctuation))
	phrase = phrase.lower()
	list_words = phrase.split(' ')
	counts = {}
	for word in list_words:
		if word != '':
			if word in counts:
				counts[word] += 1
			else:
				counts[word] = 1
	return counts
