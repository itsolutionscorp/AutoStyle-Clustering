def slices(num, size):
	if size > len(num):
		raise(ValueError)
	num = map(int, list(num))

	return [num[i:i+size] 
			for i in range(len(num)+1-size)]


def largest_product(num, size):
	if not num:
		return 1
	num = slices(num, size)
	
	return max(map(lambda z: reduce(lambda x, y: x * y, z), num))
