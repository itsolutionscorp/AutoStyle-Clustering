def largest_product(numbers, size):
	largest = 0
	for nslice in slices(numbers, size):
		mult = reduce(lambda x,y: x*y, nslice, 1)
		if mult > largest:
			largest = mult
	return largest

def slices(numbers, size):
	if size > len(numbers):
		raise ValueError
	return [map(int, numbers[i:i+size]) for i in range(len(numbers) - size + 1)]
