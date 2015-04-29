import re
def word_count(phrase):
	wordcount = []
	result = {}
	lower = phrase.lower()
	wordlist = re.split('[!@#$%^&:,. ]', lower)
	while '' in wordlist:
		wordlist.remove('')
	for word in wordlist:
		wordcount.append(wordlist.count(word))
	result = dict(zip(wordlist, wordcount))
	return result
