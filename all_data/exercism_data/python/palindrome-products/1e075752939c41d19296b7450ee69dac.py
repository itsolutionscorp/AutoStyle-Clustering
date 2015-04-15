def smallest_palindrome(max_factor, min_factor=0):
	return min(get_palindromes(max_factor, min_factor), key=lambda p: p[0])

def largest_palindrome(max_factor, min_factor=0):
	return max(get_palindromes(max_factor, min_factor), key=lambda p: p[0])

def get_palindromes(max_factor, min_factor=0):
	for x in xrange(min_factor, max_factor + 1):
		for y in xrange(x, max_factor + 1):
			v = x * y
			if str(v) == str(v)[::-1]:
				yield (v, (x, y))
