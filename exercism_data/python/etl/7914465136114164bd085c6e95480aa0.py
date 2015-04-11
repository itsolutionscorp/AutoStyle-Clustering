def transform(old):
	r = {}
	for ind,l in old.items():
		for x in l:
			r[x.lower()] = ind
	return r
