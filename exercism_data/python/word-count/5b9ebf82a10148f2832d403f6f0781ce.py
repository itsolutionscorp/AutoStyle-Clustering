"""
 Solution for "Word Count" Exercise
"""
def containsKey(key, dic):
	for k in dic.keys():
		if k == key:
			return True

	return False

def word_count(input):
	result = {}
	words = input.replace('\n',' ').split(' ')
	
	for word in words:
		if word != '':
			if(containsKey(word, result)):
				result[word] += 1
			else:
				result[word] = 1

	return result
