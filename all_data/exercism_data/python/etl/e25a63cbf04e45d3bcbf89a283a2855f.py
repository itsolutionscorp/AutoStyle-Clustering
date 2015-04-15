def transform(old):
	new = {}
	for value, words in old.iteritems():
		for w in words:
			new[w.lower()] = value
	return new
