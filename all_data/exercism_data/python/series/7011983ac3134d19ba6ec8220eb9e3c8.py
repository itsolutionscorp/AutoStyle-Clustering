def slices(elements, step):
	if not step or len(elements) < step:
		raise ValueError

	wrapper = []

	while len(elements) >= step:
		bite = elements[:step]
		wrapper.append([int(i) for i in bite])
		elements = elements[1:]

	return wrapper
