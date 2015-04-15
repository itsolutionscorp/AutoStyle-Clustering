def parse_binary(input):
	result = 0
	input = input[::-1]
	for i in range(len(input)):
		if input[i] not in '10':
			raise ValueError('Not a valid binary number')
		result += int(input[i])*(2**i)
	return result
