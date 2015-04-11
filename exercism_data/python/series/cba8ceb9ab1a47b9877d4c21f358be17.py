def slices(args, length):

	print args
	if length > len(args):
		raise ValueError('Series length is longer than the number of digits.') 
	if length < 1:
		raise ValueError('Series length must be greater than 0.')

	slice_list = []
	for i in range(0, len(args) - length + 1):
		slice_list.append(args[i:i+length])

	slice_list = [map(int, x) for x in slice_list]
	return slice_list
