def word_count(sentence):
	counts = {}
	for word in sentence.split():
		counts[word] = 1 + counts.get(word, 0)
		
	return counts

def _word_count(words):
	for word in words.split():
		if word not in wordcount:
			wordcount[word] = 1
		else:
			wordcount[word] += 1
