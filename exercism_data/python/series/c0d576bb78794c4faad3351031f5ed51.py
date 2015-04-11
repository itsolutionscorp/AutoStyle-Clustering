def slices(digit_string, slice_len):
	'''
	Take a string of digits and return all substrings of length `n` digits in that string
	'''
	
	dig_str_len = len(digit_string)
	if not 0 < slice_len <= dig_str_len:
		raise ValueError()

	digit_list = [int(ii) for ii in digit_string]
	
	series_list = [digit_list[ii:ii+slice_len] for ii in range(dig_str_len-slice_len+1)]
	
	return series_list
	
if __name__ == '__main__':
	print(slices('987654321',4))
	print(slices('ABCDEFGHI',5))
