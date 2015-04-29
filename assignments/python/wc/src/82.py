import string
def word_count(phrase):
	punct = set(string.punctuation)
	phrase = ''.join(x for x in phrase if x not in punct)
	words = phrase.split()
	word_count = {}
	for word in words:
		''.join(x for x in word if x not in punct)
		word = word.lower()
		if word in word_count:
			word_count[word] += 1
		else:
			word_count[word] = 1
	return word_count
