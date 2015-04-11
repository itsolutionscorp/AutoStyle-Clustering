#
# Skeleton file for the Python "word_count" exercise.
#

def word_count(phrase):
	word_dict = {}
	word_list = phrase.split( )
	for word in word_list:
		if word in word_dict:
			word_dict[word] = word_dict[word] + 1
		else:
			word_dict[word] = 1
	return word_dict
