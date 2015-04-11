def slices(digits, slice_length):	
	if slice_length == 0 or slice_length > len(digits):
		raise ValueError("Incorrect slice length provided:", slice_length)
	else:
		series = []
		i = 0	
		while i <= len(digits) - slice_length:
			slice = digits[i:i+slice_length]		
			slice = map(int, list(slice))				
			series.append(slice)
			i += 1
		return series
