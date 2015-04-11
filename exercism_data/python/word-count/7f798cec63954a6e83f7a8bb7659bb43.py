# Takes the number of words in a string and counts their occurrence
import pdb

def word_count(word):
	# First create a list of all words in the phrase
	word_list = word.split()
	word_dict = {}
	return_string = ""
	# Next we need to iterate through the list, and remove any duplicates
	for words in word_list:
		# pdb.set_trace()
		word_count = word_list.count(words)
		if not word_dict.has_key(words):
			word_dict[words] = (word_count)
	# for words in word_dict:
	# 	# print word_dict
	# 	return_string +=  "%s: %s\n"  %(words, str(word_dict[words]))  <-- This is commented out becasue I thought the task was to present the dictionary as a string
	return word_dict

# print word_count('car : carpet as java : javascript!!&@$%^&')
