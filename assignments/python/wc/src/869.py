def word_count(phrase):
	phrase = phrase.split()
	count = {} 	
	for i in phrase:
		if i in count:
			count[i] += 1
		else:
			count[i] = 1
	return count
