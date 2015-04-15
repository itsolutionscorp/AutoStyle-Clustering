def word_count(string):
	d = dict()
	array = string.split()
	for i in array:
		if i in d.keys():
			d[i] = d[i] + 1
		else:
			d[i] = 1
	return d
