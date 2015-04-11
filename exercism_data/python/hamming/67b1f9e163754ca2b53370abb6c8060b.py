import operator

def distance(string1, string2):
	return map(operator.eq, string1, string2).count(False)
