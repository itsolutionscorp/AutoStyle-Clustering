def word_count(string):
	keys = string.split()
	d = dict()
	for k in keys:
		d[k] = 0
	for k in keys:
		d[k] += 1
	return d
