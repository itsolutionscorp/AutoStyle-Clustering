def slices(s, slice_length):
	if len(s) < slice_length or slice_length <= 0:
		raise ValueError

	# All the possible string slice beginning indices
	beginnings = range(0, len(s) - slice_length + 1)

	# All the strings of length slice_length starting from the
	# index values in beginnings
	sliced_strings = [s[x:x+slice_length] for x in beginnings]

	# Turn every character into a digit through applying the 
	# function `int' to it.
	sliced_ints = [map(int, entry) for entry in sliced_strings]
	return sliced_ints
