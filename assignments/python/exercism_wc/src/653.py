def word_count(phrase):
	counts = {}
	phrase = phrase.split()
	for word in phrase:
		if not word:
			continue
		count_word = counts.get(word, 0)		
		count_word += 1
		counts[word] = count_word
	return counts
