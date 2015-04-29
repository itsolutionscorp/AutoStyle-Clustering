from operator import mul
def slices(series, length):
	if length > len(series) or length < 0:
                raise ValueError("%s is shorter than %d characters",series, length)
	return [map(int,series[i:i+length]) for i in range(0,len(series)-length+1)]

def largest_product(digits, size):
	if size > len(digits) or size < 0:
                raise ValueError("%s is shorter than %d characters",digits, size)
	return max([reduce(mul,subset,1) for subset in slices(digits,size)])
