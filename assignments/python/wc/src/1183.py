import string
def word_count(phrase):
	punc = string.punctuation
	s = list(phrase)
	words = ''.join([o for o in s if not o in punc]).lower().split()
	count_words = {}
	for i in words:
		if i in count_words:
			num = count_words[i]
			del count_words[i]
			count_words[i] = num + 1
		else:
			count_words[i] = 1
	return count_words
