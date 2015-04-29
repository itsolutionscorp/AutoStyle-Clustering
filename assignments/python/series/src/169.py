def slices(number_string, series_length):
	if series_length > len(number_string):
		raise ValueError('Series length should not be larger than string length.')
	elif series_length < 1:
		raise ValueError('Series length should not be less than one.')
	numberstring_list_as_ints = [int(i) for i in list(number_string)]
	return [(numberstring_list_as_ints[i:i+series_length]) for i in range(0,len(numberstring_list_as_ints) - series_length + 1)]
