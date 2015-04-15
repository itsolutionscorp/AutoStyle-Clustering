def accumulate(inp, fn):
	a = []
	for x in inp:
		a += [fn(x)]
	return a
