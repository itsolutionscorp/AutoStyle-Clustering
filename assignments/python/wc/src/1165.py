from collections import Counter
def word_count(sentence):
	wordlist = sentence.split()
	count = Counter()
	for word in wordlist:
		count[word] += 1
	return count
