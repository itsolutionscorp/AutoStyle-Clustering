import re
def word_count(string):
	word_count_dict = {}
	strings_list=re.split('\s+', string)
	for string in strings_list:
		if string not in word_count_dict:
			word_count_dict[string]=1
		else:
			word_count_dict[string]+=1
	return word_count_dict
