# counts the instances of each word in a string

def word_count(phrase):
	words = phrase.split()
	set_of_words = set(words)
	answer = {}

	for word in set_of_words :
		word = word.rstrip()
		if word :
			counter = 0;
			for other_word in words :
				if word == other_word: counter += 1
			answer[word] = counter
	
	return answer
