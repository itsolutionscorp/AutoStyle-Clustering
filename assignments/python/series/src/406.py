def slices(digit_string, slice_len):
	dig_str_len = len(digit_string)
	if dig_str_len==0 or slice_len==0 or slice_len>dig_str_len or slice_len>10:
		raise ValueError()
	digit_list = [int(ii) for ii in digit_string]
	series_list = [digit_list[ii:ii+slice_len] for ii in range(dig_str_len-slice_len+1)]
	return series_list
