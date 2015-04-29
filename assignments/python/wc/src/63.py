def word_count(str):
	a = str.split()
	return {i : a.count(i) for i in a}
