def transform(old):
	new = {}
	for a, b in old.items():
		for x in b:
			new[x.lower()] = a
	return new
