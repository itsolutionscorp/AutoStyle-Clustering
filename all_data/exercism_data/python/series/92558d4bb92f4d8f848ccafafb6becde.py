def slices(numbers, length):
	n_series = []
	num = [int(x) for x in list(numbers)]

	validate_input(num, length)

	for i in range(len(num) - length + 1):
		n_series.append(num[i:(i+length)])
	return n_series

def validate_input(n, l):
	if len(n) < l:
		raise ValueError("The length argument did not fit the series. %d < %d" %(len(n), l))
	elif l == 0:
		raise ValueError("Your length must be greater than zero. Your length was %d" %(l))
