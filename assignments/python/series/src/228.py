def slices(string, slice_length):
	if slice_length > len(string) or slice_length == 0:
		raise ValueError(-1)
	number_of_slices = len(string) - slice_length + 1
	slice_index = 0
	series = []
	for slice in range(number_of_slices):
		series.append(map(int, list(string[slice_index:slice_index + slice_length])))
		slice_index += 1
	return series
