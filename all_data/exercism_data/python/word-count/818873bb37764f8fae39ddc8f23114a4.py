__author__ = 'benlarue'


def word_count(inp):
	new_inp = inp.strip()
	words = new_inp.split()
	count = {}
	for word in words:
		word = word.strip()
		if word:
			if word in count:
				count[word] += 1
			else:
				count[word] = 1
	return count
