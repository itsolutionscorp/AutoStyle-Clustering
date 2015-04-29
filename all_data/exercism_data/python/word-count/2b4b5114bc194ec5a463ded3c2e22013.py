def word_count(phrase):
	words = _normalize(_clean(phrase)).split()
	count = {}
	for word in words:
		try:
			count[word] += 1
		except KeyError:
			count[word] = 1
	return count

def _clean(string):
	return ''.join([char for char in string if char.isalnum() or char.isspace()])

def _normalize(string):
	return string.lower()
