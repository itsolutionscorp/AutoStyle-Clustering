def palindrome(select_func, max_factor=None, min_factor=1):
	palindromes = [ (a*b, [a, b]) 
		for a in range(min_factor, max_factor+1)
		for b in range(a, max_factor+1)
		if str(a*b) == str(a*b)[::-1]]
	return select_func(palindromes)

def smallest_palindrome(**kwargs):
	return palindrome(min, **kwargs)

def largest_palindrome(**kwargs):
	return palindrome(max, **kwargs)
