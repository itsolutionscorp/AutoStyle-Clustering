def word_count(string):
	dictonary = {}

	for word in string.split():
		if not word in dictonary: dictonary[word] = 1
		else: dictonary[word] += 1
	return dictonary
