def slices(number_string, length):
	if length > len(number_string):
		raise ValueError('Slice length should not be larger than string length.')
	elif length < 1:
		raise ValueError('Slice length should not be less than one.')
	numberstring_list_as_ints = [int(i) for i in list(number_string)]
	list_of_lists = []
	for i in range(0,len(numberstring_list_as_ints)):
		potential_series = (numberstring_list_as_ints[i:i+length])
		if len(potential_series) == length:
			list_of_lists.append(potential_series)
	return list_of_lists
