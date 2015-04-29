def distance(strand1, strand2):

	if len(strand1) != len(strand2):
		raise Exception('length mismatch', len(strand1), len(strand2))

	hd = 0

	for i in xrange(len(strand1)):
		if strand1[i] != strand2[i]:
			hd += 1

	return hd
