def word_count(input):
	words = input.split()
	d = {}
	for word in words:
		d[word] = 0
	for word in words:
		d[word] = d[word] + 1
	return d
