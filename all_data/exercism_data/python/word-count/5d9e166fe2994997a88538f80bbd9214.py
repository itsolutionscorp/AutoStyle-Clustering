from collections import defaultdict

def word_count(input):
	wordsFreq = defaultdict(str)
	word = input.split(" ")
	wordsFreq[word] += 1
	return wordsFreq
