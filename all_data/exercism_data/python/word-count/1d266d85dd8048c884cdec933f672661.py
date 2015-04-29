def word_count(phrase):
	phrase_dict = dict()
	
	for word in phrase.split():
		if word in phrase_dict:
			phrase_dict[word] += 1
		else:
			phrase_dict[word] = 1
			
	return phrase_dict
