def slices(string, length):
	if length < 1 or length > len(string):
		raise ValueError
	mainList = []
	# loop through the string to the last possible start of a substring
	for i in range(0,len(string)-length+1):
		# create a new substring
		subList = []
		for j in range(0,length):
			# build the substring from integers
			subList.append(int(string[i+j]))
		mainList.append(subList)
	return mainList
