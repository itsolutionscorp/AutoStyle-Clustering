def slices(series, length):
	if length > len(series) or length <= 0:
		raise ValueError("%s is shorter than %d characters",series, length)
	returnArray = []
	for i in range(0,len(series)-length+1):
		returnArray.append(map(int,list(str(series[i:i+length]))))
	return returnArray
