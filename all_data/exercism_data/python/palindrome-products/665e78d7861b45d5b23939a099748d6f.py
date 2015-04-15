def largest_palindrome(max_factor=0, min_factor=0):
	res = {}
	for a in range(min_factor, max_factor+1):
		for b in range(a, max_factor+1):
			product,factors = checker(a,b)
			if factors != 0:
				res.setdefault(product,[]).append((a,b))
	return (max(res),res[max(res)][0])

def smallest_palindrome(max_factor=0, min_factor=0):
	res = {}
	for a in range(min_factor, max_factor+1):
		for b in range(a, max_factor+1):
			product,factors = checker(a,b)
			if factors != 0:
				res.setdefault(product,[]).append((a,b))
	return (min(res),res[min(res)][0])



def checker(a,b):
	p,q = str(a*b),a*b
	if p == p[::-1]:
		return q, [a,b]
	return 0,0
	
print largest_palindrome(10)
