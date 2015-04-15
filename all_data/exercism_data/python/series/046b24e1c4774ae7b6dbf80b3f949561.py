def slices(series, length):
	l = len(series)
	if length > l or length <= 0:
		raise ValueError("Invalid Length: Must be between 0 and {0}: {1}".format(l, length))
	
	series = [int(a) for a in series] # convert '1234' to [1,2,3,4]
	return [ series[i:i+length] for i in range(l - length + 1)] # a series with n length has n-l+1 number of l-length words
