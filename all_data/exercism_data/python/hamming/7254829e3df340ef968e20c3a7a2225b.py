def distance(seg1, seg2):
	count = len([i for i in range(len(seg1)) if seg1[i] != seg2[i]])
	return count
