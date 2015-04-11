def word_count(sentence):
	words = sentence.split()
	base_words = remove_repeated(words)
	word_count = dict()
	for word in base_words:
		word_count[word] = words.count(word)
	return word_count

def remove_repeated(words):
	#Removes repeated words.
	repeated = []
	return [word for word in words if not (word in repeated or repeated.append(word))]
