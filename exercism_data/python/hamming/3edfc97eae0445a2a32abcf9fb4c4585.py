def distance(a,b):
	c = 0
	for i in range(len(a)):
		if a[i] != b[i]: c+=1
	return c
