def slices(digits, n): 
	"""
	Takes a string of digits and the length n of series you wish to find.  
	Outputs a list containing all series' of length n from that string.
	"""

	if n > len(digits ) or n <= 0: 
		raise ValueError("n must be an integer between 0 and the length of the string of digits")
	
	list = []

	number_of_lists = len(digits) - n + 1
	
	for x in range(0, number_of_lists): 
		list.append([])

	for counter in range(0 , number_of_lists): 
		for j in range(counter, counter + n): 
			list[counter].append(int(digits[j]))
	
	return list
	
slices("0123", 2)


		
	
