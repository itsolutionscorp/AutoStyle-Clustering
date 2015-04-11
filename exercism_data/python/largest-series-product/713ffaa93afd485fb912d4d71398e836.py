def slices(sequence, length):
	'''Return the input string's subsequences of a given length.'''
	subsequences = []

	if (len(sequence) < length):
		raise ValueError("Subsequence length must not be longer than string.")

	# Build a list of all sublists, where a sublist is a sequence of characters.
	for start in range(len(sequence)-length+1):
		subsequence = [int(sequence[char]) for char in range(start, start+length)]
		subsequences.append(subsequence)

	return subsequences

def largest_product(sequence, length):
	'''Return the largest product of a sequence of the given length of integers.'''
	slice_set = slices(sequence, length)

	product_set = map(n_product, slice_set)
	product_max = max(product_set)

	return product_max

def n_product(terms):
	product = 1
	for term in terms:
		product = product*term

	return product
