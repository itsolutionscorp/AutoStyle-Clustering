def distance(seq_a, seq_b):

	count = 0
	for i, nucleo in enumerate(seq_a):
		if seq_b[i] is not nucleo:
			count += 1
	return count
