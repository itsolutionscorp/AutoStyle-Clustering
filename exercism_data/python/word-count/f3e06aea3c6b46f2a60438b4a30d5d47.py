def word_count(text):
	result = {}
	words = filterText(text).split(" ")
	for word in words:
		if len(word) == 0:
			continue
		if word in result:
			result[word] = result[word] + 1
		else:
			result[word] = 1
	return result

def filterText(text):
	result = ""
	for char in text:
		if char == '\n':
			result = result + ' '
			continue
		result = result + char
	return result
