def slices(number, length):
	if length <= len(number) and length != 0:
		slices = []
		for count in range(0, len(number) - length + 1):
			x = number[count:count + length]
			slices.append(map(int, list(x)))
		return slices
	else:
		raise ValueError("Incorrect length")
