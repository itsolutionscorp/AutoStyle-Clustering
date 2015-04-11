import re

def word_count(phrase):
	split_string = re.split('\W+', phrase)
	counted_phrase = {}
	lower_list = [x.lower() for x in split_string]
	clean_list = filter(None, lower_list)

	for x in clean_list:
		counted_phrase[x] = lower_list.count(x)
	return counted_phrase	
