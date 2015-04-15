def slices(digits,n):
	if(n > len(digits) or n < 1):
		raise ValueError
	return [[int(d) for d in digits[i:i+n]] for i in range(len(digits)-n+1)]
