def slices(s, n):
	if n < 0 or n > len(s):
		raise ValueError
	l = [int(c) for c in s]
	results = []
	for i in xrange(n, len(l)+1):
		results.append(l[i-n:i])
	return results

def product(s):
	result = 1
	for c in s:
		result *= int(c)
	return result

def largest_product(s, n):
	candidates = [product(slice) for slice in slices(s, n)]
	return max(candidates)
