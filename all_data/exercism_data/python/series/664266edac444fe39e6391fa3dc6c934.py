def slices(series, length):
	# series comes in as a string so it can be treated as a list
	if length > len(series) or length == 0:
		raise ValueError("%s is shorter than %d characters",series, length)

	returnArray = []
	# Go through each possible set
	for i in range(0,len(series)-length+1):
		# append a list of ints for each possible subset of the series
		returnArray.append(map(int,list(str(series[i:i+length]))))

	return returnArray
