def largest_product(digits, n):
	if n > len(digits):
		raise ValueError()
		
	largest = -1
	for k in range(0,len(digits)-n+1):
		c = 1
		for j in range(0,n):
			c *= int(digits[k+j])
		if c > largest:
			largest = c
	return largest
	
def slices(digits, n):
	if n > len(digits):
		raise ValueError("Sizes error")
	c = []
	for k in range(0,len(digits)-n+1):
		sc = []
		for j in range(0,n):
			sc.append(int(digits[k+j]))
		c.append(sc)

	return c
