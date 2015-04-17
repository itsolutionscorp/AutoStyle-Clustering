def word_count(s):
	l = s.split()
	d = {}
	for item in l:
		if item in d.keys():
			d[item] += 1
		else:
			d[item] = 1
	return d
