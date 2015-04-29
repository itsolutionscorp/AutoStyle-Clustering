def word_count(string):
	tokens = string.split()
	dict = {}
	for s in tokens:
		if dict.has_key(s):
			total = dict.get(s) + 1
			dict[s] = total
		else:
			dict[s] = 1
	return dict
