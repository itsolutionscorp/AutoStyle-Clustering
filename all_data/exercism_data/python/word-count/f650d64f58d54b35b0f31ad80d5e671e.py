def word_count(phrase):
	phrase=phrase.split()
	words={}

	for word in phrase:
		if word in words:
			words[word]+=1
		else:
			words.update({word:1})

	return words
