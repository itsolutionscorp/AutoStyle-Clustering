def slices(seq, length):
	# slice of length occurs len(seq)-length+1 times
	n = len(seq)
	if length > n or length <= 0:
		raise ValueError("Invalid Length: Must be between 0 and {0}: {1}".format(n, length))
	seq = [int(x) for x in seq]
	return [seq[i:i+length] for i in range(len(seq)-length+1)]

def largest_product(seq, length):
	return max([reduce(lambda x,y: x*y, s) for s in slices(seq, length)]) if seq else 1
