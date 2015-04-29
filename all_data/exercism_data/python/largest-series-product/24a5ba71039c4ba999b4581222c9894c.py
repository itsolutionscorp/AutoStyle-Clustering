from operator import mul

def largest_product(elements, step):
	if not step:
		return 1
	return max(reduce(mul, element) for element in slices(elements, step))

def slices(elements, step):
	length = len(elements)
	if not step or length < step:
		raise ValueError
	elements = map(int, elements)
	return [elements[i: i + step] for i in range(length - step + 1)]
