''' series.py
	created Oct 3 2014
	by @jestuber '''
def slices(digits, n):
	series = []
	if len(digits) < n:
		raise ValueError('%i is not less than or equal to %s' % (n,digits))
	if n <= 0:
		raise ValueError('%s must be positive' % (n))
	for i in range(0,len(digits)-n+1):
		series.append([int(x) for x in digits[i:i+n]])
	return series
