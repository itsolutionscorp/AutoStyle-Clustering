def slices(string, length):
	if length < 1 or length > len(string):
		raise ValueError
	mainList = []
	for i in range(0,len(string)-length+1):
		subList = []
		for j in range(0,length):
			subList.append(int(string[i+j]))
		mainList.append(subList)
	return mainList
