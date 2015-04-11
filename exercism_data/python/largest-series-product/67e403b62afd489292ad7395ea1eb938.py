from operator import mul

def slices(number, length):
	if length <= len(number) and length != 0:
		slices = []
		for count in range(0, len(number) - length + 1):
			x = number[count:count + length]
			slices.append(map(int, list(x)))
		return slices
	elif length == 0 and number == "":
		slices = []
	else:
		raise ValueError("Incorrect length")

def largest_product(number, length):
	maxproduct = 1
	seperatedlist = slices(number, length)
	if seperatedlist:
		for sublist in seperatedlist:	
			newproduct = reduce(mul, sublist, 1)
			if newproduct > maxproduct:
				maxproduct = newproduct
	else:
		maxproduct = 1
	return maxproduct
		
