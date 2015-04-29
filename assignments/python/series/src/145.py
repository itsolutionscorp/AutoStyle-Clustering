def slices(series, length):
	l = len(series)
	if length > l or length <= 0:
		raise ValueError("ValueError: length input must be between 0 and %d" % (length))
	else:
		SLICES_LIST = []
		index = 0
		while(length+index <= l):
			SLICE = series[index:index+length] # substring e.g: '123'
			SLICES_LIST.append([ int(d) for d in SLICE ] ) # list comprehention [1,2,3] added to SLICES_LIST
			index +=1
		return SLICES_LIST	
