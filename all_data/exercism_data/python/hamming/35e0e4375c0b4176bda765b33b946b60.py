def hamming(old, new):
	count = 0
	for e in range(min(len(old),len(new))):
		if old[e] != new[e]:
			count += 1
	count += abs(len(old)-len(new))
	return count
