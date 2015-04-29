import re
def word_count(sentence):
	sentence = sentence.lower()
	data = re.sub(u'\W', ' ', sentence, flags=re.UNICODE)
	data = data.split(' ')
	words = {}
	for x in data:
		if x != '':
			if x in words:
				words[x] = words[x] + 1
			else:
				words[x] = 1
	return words
