def detect_anagrams(word,words):
    
	length = len(words)
	the_word = word.lower()
	sorted_word = sorted(the_word)
	results = []
    
	for i in range(0,length):
		current_word = words[i].lower()
		if (the_word != current_word and sorted_word == sorted(current_word)):
			#print sorted_word, " matches ", words[i]
			results.append(words[i])

	return results
