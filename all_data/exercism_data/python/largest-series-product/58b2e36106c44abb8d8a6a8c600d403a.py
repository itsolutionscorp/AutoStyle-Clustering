def slices(num, slice):
	if slice > len(num):
		raise ValueError('slice cannot be longer than input.')
	res = []
	for i in range(len(num)+1-slice):
		res.append([int(x) for x in num[i:i+slice]])
	return res

def largest_product(num, slice):
	slice_list = slices(num, slice)
	res = 0
	for x in slice_list:
		prod = product(x)
		if prod > res:
			res = prod
	return res

def product(x):
	res = 1
	for i in x:
		res *= i
	return res
