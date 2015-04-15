def hamming(a, b):

	value = 0

	if a == b:
		return value

	a = list(a)
	b = list(b)
	
	while len(b) > len(a):
		b.pop()
		value += 1

	while len(a) > len(b):
		a.pop()
		value += 1

	while a:

		if a.pop() != b.pop():
			value += 1

	return value
