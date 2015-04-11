def transform(old):
	new = {}
	for (score, words) in old.items():
		for w in words:
			new[w.lower()] = score
	return new
