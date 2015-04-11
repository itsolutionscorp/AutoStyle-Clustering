def slices(string, combos):
	# return [map(int, list(i)) for i in combinations(set(string), combos)]
	# use string slice and pop to remove the first item
	lengthString = len(string)
	if not combos or lengthString<combos:
		raise ValueError
		
	newList = []
	listedIntegers = map(int, list(string))
	for i in xrange(lengthString-combos+1):
		newList.append(listedIntegers[0:combos])
		listedIntegers.pop(0)
	return newList


# print(slices("01234", 1))
# print(slices("97867564", 3))
# slices("012", 4)
slices("01234",0)
