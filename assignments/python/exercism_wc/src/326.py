def word_count(input):
	wordlist = input.split()
	counter = {x : wordlist.count(x) for x in set(wordlist)}
	return counter
