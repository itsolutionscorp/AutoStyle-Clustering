def slices(s, slice_length):
	if len(s) < slice_length or slice_length <= 0:
		raise ValueError
	beginnings = range(0, len(s) - slice_length + 1)
	sliced_strings = [s[x:x+slice_length] for x in beginnings]
	sliced_ints = [map(int, entry) for entry in sliced_strings]
	return sliced_ints
