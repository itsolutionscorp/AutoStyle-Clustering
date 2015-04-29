import operator

def slices(numbers, size):
	if size > len(numbers):
		raise ValueError("slice length cannot be greater than number string")
	else:
		numbers = [int(numbers[i]) for i in range(len(numbers))]
		results = []
		for i in range(len(numbers)):
			if len(numbers[i:i+size]) == size:
				results.append(numbers[i:i+size])

		return results

def largest_product(numbers, size):
	slicelist = slices(numbers, size)
	if not slicelist:
		return 1
	else:
		return max([product(i) for i in slicelist])

def product(numlist):
	return reduce(operator.mul, numlist)
