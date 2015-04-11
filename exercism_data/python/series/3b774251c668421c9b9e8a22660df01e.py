def separate(string):
	'''
	This takes the string and turns it in to a list of lists.
	Also converts the chars to ints.
	'''
	returnList = []
	for d in string:
		returnList.append([int(d)])
	return returnList

def slices(inputString, sliceSize):
	'''
	Takes a string of digits and gives all the possible series of length `n`
	'''
	#Checks for valid slice size
	if len(inputString) < sliceSize or sliceSize < 1:
		raise ValueError('Invalid Slice Size')
	
	#separates input in to a list
	individualList = separate(inputString)
	returnList = []
	
	# for each element in the list, add consecutive values n number of times
	for i in range(len(individualList)-sliceSize+1):
		returnList.append(individualList[i])
		for j in range(1,sliceSize):
			returnList[i] += individualList[:][j+i]
	return returnList
