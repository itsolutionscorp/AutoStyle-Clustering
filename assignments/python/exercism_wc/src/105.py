def word_count(phrase):
	counts = {}
	phrase = ' '.join(phrase.split("\n"))
	for word in phrase.split(' '):
		if word:
			if word in counts:
				counts[word] += 1
				continue
			counts[word] = 1
	return counts
