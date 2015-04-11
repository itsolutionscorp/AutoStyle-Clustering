import collections
def word_count(sentence):
	sentence_list = sentence.split()
	freq = collections.defaultdict(int)
	for word in sentence_list:
		freq[word] += 1
	freq.update(freq.items())
	return freq
