def distance(s1, s2):

	return reduce(
		lambda x, y: x + y,
		map(
			lambda x: x[0] != x[1],
			zip(s1, s2)
		)
	)
