from collections import Counter
def word_count(sentence):
	dict = Counter()
	for word in sentence.split():
		dict[word] += 1
	return dict
