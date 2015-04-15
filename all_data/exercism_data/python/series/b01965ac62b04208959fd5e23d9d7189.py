def slices(sequence, length):
	""" This function takes a sequence of numbers and returns all possible slices of a certain length as a		 list. If the length is greater than the length of the sequence, a value error is raised """
	
	
	if length > len(sequence) or length == 0:
		raise ValueError('Length is greater than sequence')
	
	slices = []
	holder_list = []
	index_counter = 0
	
	while index_counter + length <= len(sequence):
		for i in range(length):
			holder_list.append(int(sequence[index_counter + i]))
		slices.append(holder_list)
		holder_list = []
		index_counter += 1
	
	return slices
