import pdb
def word_count(word):
	word_list = word.split()
	word_dict = {}
	return_string = ""
	for words in word_list:
		word_count = word_list.count(words)
		if not word_dict.has_key(words):
			word_dict[words] = (word_count)
	return word_dict
