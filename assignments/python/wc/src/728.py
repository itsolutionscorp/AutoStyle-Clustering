def word_count(phrase):
	phrase_dict = dict()
	one_space_phrase = " ".join(phrase.split())
	split_phrase = one_space_phrase.split()
	for word in split_phrase:
		if word in phrase_dict:
			phrase_dict[word] += 1
		else:
			phrase_dict[word] = 1
	return phrase_dict
