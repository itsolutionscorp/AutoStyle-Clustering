def slices(number, length):
	if length <= len(number) and length != 0:
		slices = []
		for count in range(len(number) - length + 1):
			x = number[count:count + length] # grabs the next slice of length "length"
			slices.append(map(int, list(x))) # converts the slide to a number and adds it to the list of slices
		return slices
	else:
		raise ValueError("Incorrect length")
