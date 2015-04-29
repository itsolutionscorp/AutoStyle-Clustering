# series.py

import operator

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

def product(iterable):
    return reduce(operator.mul, iterable, 1)

def largest_product(series, length):
	if length == 0:
		return 1
	try:
		sliced = slices(series, length)
	except:
		raise
	largest = 0
	for slice in sliced:
		if product(slice) > largest:
			largest = product(slice)
	return largest
	
