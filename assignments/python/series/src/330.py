from itertools import combinations
def slices(string, combos):
	lengthString = len(string)
	try:
		if lengthString >= combos:
			newList = []
			listedIntegers = map(int, list(string))
			for i in xrange(lengthString-combos+1):
				newList.append(listedIntegers[0:combos])
				listedIntegers.pop(0)
			return newList
	except ValueError:
		print("combos value must be lower than the length of string")
print(slices("012", 4))
