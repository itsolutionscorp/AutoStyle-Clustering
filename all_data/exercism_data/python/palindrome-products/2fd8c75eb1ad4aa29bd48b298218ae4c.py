def palindrome(select_func, **kwargs):
	minf = kwargs.get('min_factor', 1)
	maxf = kwargs['max_factor']
	palindromes = [ (a*b, [a, b]) 
		for a in range(minf,maxf+1)
		for b in range(minf,maxf+1)
		if a >= b
		and str(a*b) == str(a*b)[::-1]]
	return reduce(select_func, palindromes)

def smallest_palindrome(**kwargs):
	return palindrome(min, **kwargs)

def largest_palindrome(**kwargs):
	return palindrome(max, **kwargs)
