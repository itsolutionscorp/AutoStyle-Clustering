def slices(string, combos):
	lengthString = len(string)
	if not combos or lengthString<combos:
		raise ValueError
	newList = []
	listedIntegers = map(int, list(string))
	for i in xrange(lengthString-combos+1):
		newList.append(listedIntegers[0:combos])
		listedIntegers.pop(0)
	return newList
slices("01234",0)
