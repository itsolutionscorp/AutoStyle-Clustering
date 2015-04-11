def word_count(phrase):
	word_counter = {}	
	split_phrase = phrase.replace('\n', ' ').split(' ')

	for word in split_phrase:
		if word == '':
			continue
		if word in word_counter:
			word_counter[word] += 1
		else:
			word_counter[word] = 1
	print word_counter
	return word_counter
