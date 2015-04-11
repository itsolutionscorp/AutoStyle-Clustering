import re
def word_count(phrase):

	answer = {}

	words = str.split(phrase)

	for word in words:

		words.count(word)
		answer[word] = words.count(word)

	return answer
