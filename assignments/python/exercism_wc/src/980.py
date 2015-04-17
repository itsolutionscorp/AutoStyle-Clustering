from collections import Counter
def word_count(sentence):
	return Counter([word for word in sentence.split()])
