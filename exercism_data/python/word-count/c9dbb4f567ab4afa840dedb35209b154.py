"""
 Solution for "Word Count" Exercise
"""
def word_count(input):
	result = {}
	words = input.replace('\n',' ').split(' ')
	
	for word in words:
		if word != '':
			result[word] = result.get(word,0) + 1

	return result
