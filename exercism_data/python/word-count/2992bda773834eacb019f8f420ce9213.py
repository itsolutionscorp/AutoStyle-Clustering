def word_count (phrase):
	phrase = phrase.split()
	count={phrase.pop(0):1}
	for x in phrase:
		if count.has_key(x):
			count[x] = count[x] + 1
		else:
			count[x] = 1
	return count
