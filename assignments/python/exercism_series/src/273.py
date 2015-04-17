def slices(number_string, length):
	if length > len(number_string):
		raise ValueError('Slice length should not be larger than string length.')
	elif length < 1:
		raise ValueError('Slice length should not be less than one.')
	numberstring_list_as_ints = [int(i) for i in list(number_string)]
	list_of_lists = []
	for i in range(0,len(numberstring_list_as_ints) - length + 1):
		list_of_lists.append((numberstring_list_as_ints[i:i+length]))
	return list_of_lists
