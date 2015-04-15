

def word_count(string):
	list = string.split()
	totals = {}
	for item in list:
		n = 0
		for word in list:
			if item == word:
				n += 1
		totals[item] = n
	return totals
