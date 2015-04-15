# Takes the number of words in a string and counts their occurrence
import pdb

def word_count(words):
	# First create a list of all words in the phrase
	words = words.split()
	word_dict = {}
		# Next we need to iterate through the list, and remove any duplicates
	for word in words:		# pdb.set_trace()
		word_dict[word] = word_dict.get(word, 0) + 1
	return word_dict

# print word_count('car : carpet as java : javascript!!&@$%^&')
