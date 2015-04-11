def word_count(words):
	from collections import Counter

	wordlist = words.split()

	return Counter(wordlist)

	#wordset = set(wordlist)
	#for word in wordset:
	
	#	print word, ": ", wordlist.count(word)
