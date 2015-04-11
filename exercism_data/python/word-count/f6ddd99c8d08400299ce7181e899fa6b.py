import re

def word_count(string):
	counts = {}
	string = re.split(" |\n|\t", string)
	for word in string:
		if not word:
			continue

		if word in counts:
			count_word = counts[word]
		else:
			count_word = 0
		
		count_word += 1
		counts[word] = count_word

	return counts
