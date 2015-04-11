def slices(digits, length):
	if length <= 0:
		raise ValueError('Requested slice length is too short.')
	if length > len(digits):
		raise ValueError('Requested slice length is too long.')
	slice_list = []
	for i in range(len(digits) - length + 1):
		slice_list.append(map(int, digits[i:i+length]))
	return slice_list
