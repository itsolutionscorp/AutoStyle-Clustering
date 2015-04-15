def transform(old):
	return {word.lower(): sc for sc, words in old.iteritems() for word in words}
