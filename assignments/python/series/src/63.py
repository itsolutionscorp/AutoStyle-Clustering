def slices(s, n):
	if len(s) < n or n < 1:
		raise ValueError
	total = []
	for x in range(len(s)-n+1):
		total.append([int(d) for d in s[x:x+n:1]])
	return total
