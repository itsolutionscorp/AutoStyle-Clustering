def slices(string, slice_len):
	possible_slices = []
	
	if len(string) < slice_len:
		raise ValueError('Slice length must be less than or equal to string.')
	elif slice_len == 0:
		raise ValueError('Slice length must be greater than 0.')

	for i,char in enumerate(string):
		if i+slice_len <= len(string):
			possible_slices.append(map(int,list(string[i:i+slice_len])))

	return possible_slices
