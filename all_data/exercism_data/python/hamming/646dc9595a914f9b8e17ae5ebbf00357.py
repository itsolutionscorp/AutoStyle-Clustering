def distance(strand1, strand2):
	t_strand1 = tuple(strand1)
	t_strand2 = tuple(strand2)
	count_diff = 0

	for n, item in enumerate(t_strand1):
		n = n - 1
		if t_strand1[n] != t_strand2[n]:
			count_diff += 1
		else:
			pass
	return count_diff
