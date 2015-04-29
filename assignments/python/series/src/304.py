def slices(elements, step):
	length = len(elements)
	if not step or length < step:
		raise ValueError
	elements = map(int, elements)
	return [elements[i: i + step] for i in range(length - step + 1)]
