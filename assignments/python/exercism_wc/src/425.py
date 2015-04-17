from collections import Counter
def word_count(phrase):
	wordlist = phrase.split()
	wordfreq = [wordlist.count(w) for w in wordlist]
	return dict(zip(wordlist,wordfreq))
