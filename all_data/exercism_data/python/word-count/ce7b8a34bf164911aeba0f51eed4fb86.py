import re
def word_count(phrase):
	mapping = {}
	wordSplit = re.compile("[(\s)(\\n)]+").split(phrase)
	for word in wordSplit:
		if word in mapping:
			mapping[word] = mapping[word] + 1
		else:
			mapping[word] = 1
	return mapping
