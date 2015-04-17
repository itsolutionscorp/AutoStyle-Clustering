def slices(string, slice_length):
	if slice_length > len(string) or slice_length == 0:
		raise ValueError('slice cannot be longer than input or zero.')
	result = []
	for i in range((len(string)+1)-slice_length):
		result.append([int(x) for x in string[i:i+slice_length]])
	return result
