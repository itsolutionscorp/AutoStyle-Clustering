def slices(string,n):
	ret = []
	if not string:
		return [[1]]
	if n > len(string) or not n:
		raise ValueError
	i = 0
	while i + n <= len(string):
		ret.append([int(x) for x in string[i:i+n]])
		i += 1
	return ret

def prod(lst):
	ret = 1
	for x in lst:
		ret *= x
	return ret

def largest_product(string,n):
	return max(prod(x) for x in slices(string,n))
