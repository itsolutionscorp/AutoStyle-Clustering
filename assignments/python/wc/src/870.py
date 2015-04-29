import re
def word_count(phrase):
	wordcount = {}
	words = re.sub('[^a-zA-Z0-9\s]*', '', phrase).lower().split()
	for word in words:
		if word not in wordcount:
			wordcount[word] = 1
		else:
			wordcount[word] += 1
	return wordcount
