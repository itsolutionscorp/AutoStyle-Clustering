def word_count(phrase):
	dict = {}
	phrase = phrase.strip()
	if phrase != "":
		phrase = phrase.replace('\n',' ')
		phrase_array_all = phrase.split(' ')
		phrase_array = list(set(phrase_array_all))
		for word in phrase_array:
			if word.strip() != "":			
				for word_checking in phrase_array_all:
					if word_checking.strip() == word:
						if word in dict.keys():
							dict[word] += 1
						else:
							dict[word] = 1
	return dict
