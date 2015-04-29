'''
	This function takes in a dictionary where,
	the key is the numerical score of a letter.
	Its values (in a list) are the corresponding letters.
'''
def transform(old_dict):
	result = dict()

	for key, value in old_dict.items():
		for letter in value:
			result[letter.lower()] = key
			
	return result
