def slices(numbers, length):
	n_series = []
	num = [int(x) for x in list(numbers)]
	start = 0
	increment = length
	validate_input(num, length)
	while (start < len(num)) and (len(num[start:length]) == increment):
		n_series.append(num[start:length])
		start = start + 1
		length = length + 1
	return n_series

def validate_input(n, l):
	if len(n) < l:
		raise ValueError("The length argument did not fit the series. %d < %d" %(len(n), l))
	elif l == 0:
		raise ValueError("Your length must be greater than zero. Your length was %d" %(l))
