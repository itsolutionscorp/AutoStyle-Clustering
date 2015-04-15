#finds point differences in chars between two strings

def hamming(a, b):
	counter = 0
	#finds longer string
	if (len(a) > len(b)): 
		difference = len(a) - len(b)
		counter = counter + difference
		x = 0
		while x < len(b):
			if (b[x] != a[x]): 
				counter += 1
			x += 1
	else: 
		difference = len(b) - len(a)
		counter = counter + difference
		x = 0
		while x < len(a): 
			if (a[x] != b[x]): 
				counter += 1
			x += 1
	return counter
