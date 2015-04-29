def slices(series, length):
	if length > len(series) :
		raise ValueError('Length requested greater than length of the series')
	if length == 0:
		raise ValueError('Zero length requested')
	step=0
	slices = []
	while step + length <= len(series):
		slices.append([int(num) for num in series[step:step+length]])
		step = step + 1
	return slices
