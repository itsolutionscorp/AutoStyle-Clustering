def word_count (phrase):
	count={}
	for x in phrase.split():
		if count.has_key(x):
			count[x] = count[x] + 1
		else:
			count[x] = 1
	return count
