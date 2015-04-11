
def word_count(word):
	words = word.split(' ')
	while '' in words:
		words.remove('')
	for word in words:
		if '\n' in word:
			buffer = word.split('\n')
			index = words.index(word)
			words.remove(word)
			words.insert(index,buffer[1])
			words.insert(index,buffer[0])
	count = {}
	for word in words:
		if word in count:
			count[word] += 1
		else:
			count[word] = 1
	return count
