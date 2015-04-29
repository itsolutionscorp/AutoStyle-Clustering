import re
def word_count(text):
	count = {}
	words = re.split('\n|\s+', text)
	for word in words:
		if word in count:
			count[word] += 1
		else:
			count[word] = 1
	return count
