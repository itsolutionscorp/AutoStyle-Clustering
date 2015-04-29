def word_count(text):
	import re

	text = re.sub(r'\s{1,}', ' ', text)
	text = text.split(' ')
	wordcount = {}
	for word in text:
		if word in wordcount:
			wordcount[word] += 1

		else:
			wordcount[word] = 1

	return wordcount
