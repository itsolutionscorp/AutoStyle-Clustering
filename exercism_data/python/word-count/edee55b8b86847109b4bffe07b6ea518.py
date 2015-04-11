def word_count(phrase):
	word_list = phrase.split()
	return {x: word_list.count(x) for x in set(word_list)}
