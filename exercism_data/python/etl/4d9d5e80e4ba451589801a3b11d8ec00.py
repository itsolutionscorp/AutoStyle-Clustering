from string import lower

def transform(dict):
	new_dict = {}
	for key in dict:
		for letter in dict[key]:
			new_dict[lower(letter)] = key
	return new_dict
