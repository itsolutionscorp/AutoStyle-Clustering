def isequal(x,y):
	if x==y:
		return 0
	return 1

def distance(string1, string2):
	return sum(map(isequal,string1,string2))
