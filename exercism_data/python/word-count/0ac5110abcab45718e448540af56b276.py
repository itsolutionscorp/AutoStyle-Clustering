# This function returns a dictionary with a string's unique words and the
# number of times they occur in the string.

def word_count(str):
	a = str.split()
	return {i : a.count(i) for i in a}

