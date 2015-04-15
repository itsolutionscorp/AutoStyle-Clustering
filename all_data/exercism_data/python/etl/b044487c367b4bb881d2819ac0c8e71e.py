def transform(oldDict):
	newDict = {}
	for score in oldDict:
		for value in oldDict[score]:
			newDict[value.lower()]=score
	return newDict
