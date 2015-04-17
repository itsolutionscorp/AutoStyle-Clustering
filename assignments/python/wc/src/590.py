import pdb
def word_count(words):
	words = words.split()
	word_dict = {}
	for word in words:		# pdb.set_trace()
		word_dict[word] = word_dict.get(word, 0) + 1
	return word_dict
