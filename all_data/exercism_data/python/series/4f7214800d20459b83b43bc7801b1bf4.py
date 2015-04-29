def slices(numbers, slicesize):
	numlist = []
	resultlist = []

	for i in xrange(len(numbers)):
		numlist.append(int(numbers[i]))

	for i in xrange(len(numlist)-slicesize+1):
		if not numlist[i:i+slicesize]:
			pass
		else:
			resultlist.append(numlist[i:i+slicesize])

	if not resultlist:
		raise ValueError()
	else:
		return resultlist
