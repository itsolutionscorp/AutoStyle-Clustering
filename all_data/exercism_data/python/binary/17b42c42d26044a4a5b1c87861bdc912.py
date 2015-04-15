def powers_of_two():
	n = 1
	while True:
		yield n
		n *= 2

def parse_binary(num):
	result = 0
	for v, p in zip(reversed(num), powers_of_two()):
		if v != '0' and v != '1':
			raise ValueError
		result += int(v)*p
	return result
