def slices(digits, n):
	if n == 0 or n > len(digits):
		raise ValueError

	return [[int(x) for x in digits[i:n+i]] 
	         for i in range(0, len(digits)-n+1)]
