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
	
def largest_product(digit_string, slice_len):
	'''
	Take a string of digits and calculate the largest product for any substring of length n
	'''
	if slice_len==0 and not(digit_string):
		return 1
	series_list = slices(digit_string, slice_len)
	prodmax = 0
	for series in series_list:
		prod = 1
		for n in series:
			prod *= n
		prodmax = max(prodmax,prod)
	return prodmax

if __name__ == '__main__':
	#print(slices("97867564", 2))
	#print(largest_product("0123456789", 2))
	#print(slices("012", 4))
	largest_product("", 0)
