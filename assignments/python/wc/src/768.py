def word_count(sentence):
	words = []
	current_word = ""
	for letter in sentence:
		if letter.isalnum():
			current_word += letter
		if letter == ' ' and current_word != "":
			words.append(current_word)
			current_word = ""
	if current_word != "":
		words.append(current_word)
	counts = {}
	for word in words:
		word = word.lower()
		if word in counts.keys():
			counts[word] += 1
		else:
			counts[word] = 1
	return counts
