def word_count(text):
	uniques = dict()
	for word in text.strip().split():
		if word in uniques:
			uniques[word] += 1
		else:
			uniques[word] = 1
	return uniques
