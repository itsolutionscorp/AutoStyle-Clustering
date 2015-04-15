def distance (string_a, string_b):
	i = 0
	dist = 0
	while i < len(string_a):
		if string_a[i] != string_b[i]:
			dist += 1 
			i += 1
		else:
			i += 1
	return dist
