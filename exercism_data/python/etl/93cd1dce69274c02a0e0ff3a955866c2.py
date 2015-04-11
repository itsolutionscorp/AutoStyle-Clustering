def transform(old):
	new = {}
	for key, value in old.iteritems():
		for word in value:
			new[str(word).lower()] = key

	return new
