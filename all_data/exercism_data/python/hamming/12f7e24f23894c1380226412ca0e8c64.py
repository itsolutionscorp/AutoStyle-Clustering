def hamming(a, b):
	hamm = 0;
	shortest = min(len(a), len(b))
	longest = max(len(a), len(b))
	for i in range(0, shortest):
		if a[i] != b[i]:
			hamm += 1
	hamm += longest - shortest
	return hamm
