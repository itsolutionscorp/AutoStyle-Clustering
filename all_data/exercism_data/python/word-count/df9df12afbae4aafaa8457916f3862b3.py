#
# Skeleton file for the Python "word_count" exercise.
#

def word_count(phrase):
	word_dict = {}
	word_list = phrase.split( )
	for word in word_list:
		word_dict[word] = word_dict.get(word, 0) + 1
	return word_dict
