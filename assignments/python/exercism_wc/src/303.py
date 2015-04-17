def word_count(phrase):
	results = {}
	phrase = phrase.strip()
	words = phrase.split()
	for word in words:
		if results.has_key(word):
			count = results[word]
			results[word] = count + 1
		else:
			results[word]=1
	return results
