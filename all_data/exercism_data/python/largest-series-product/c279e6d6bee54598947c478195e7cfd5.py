import operator

def slices(string, length):
	if not 1 <= length <= len(string):
		raise ValueError( "Invalid slice length: " + str(length) )

	result = []
	for n in range(0, len(string) - (length - 1)):
		result.append(map(int, list(string[n:n+length])))
	return result

def largest_product(string, length):
	if len(string) == 0 and length == 0:
		return 1
	series = slices(string, length)
	return max(reduce(lambda x,y: x*y, serie) for serie in series)
