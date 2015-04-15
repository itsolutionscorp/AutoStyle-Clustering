def hamming(a, b):

	value = 0

	if a == b:
		return value

	a = ' '.join(a)
	b = ' '.join(b)

	a = a.split()
	b = b.split()

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
