def distance(f, s):
	fl = [x for x in f]
	sl = [x for x in s]
	d = 0
	for c1, c2 in zip(f, s):
		if c1 != c2:
			d += 1
	return d
