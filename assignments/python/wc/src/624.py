from collections import Counter
def word_count(sentence):
	count = Counter()
	sentence = sentence.replace('\n',' ')
	for word in sentence.split(' '):
		count[word] += 1
	del count['']
	return count
