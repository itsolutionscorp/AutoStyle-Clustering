def sum_of_multiples(value, bases = None):
	if bases is None:
		bases = [3, 5]
	while 0 in bases:
		bases.remove(0)
	return sum(set([i * b for b in bases for i in range(1, round(value / b) + 1) if i * b < value]))
