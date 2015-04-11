def transform(old):
	new = {}
	for k, v in old.items():
		for n in v:
			new[n.lower()] = k
	return new
