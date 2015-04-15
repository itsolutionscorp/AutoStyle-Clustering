from __future__ import division

def largest_palindrome(max_factor, min_factor=0):
	return _palindrome_in_range((max_factor, min_factor), max)

def smallest_palindrome(max_factor, min_factor=0):
	return _palindrome_in_range((min_factor, max_factor), min)

def _palindrome_in_range(search_range, better):
	"""
	Given:
		a search_range (a, b) for the factors
			the a-end of the range should yield the worse
			palindrome (see second parameter) 
		a function better(a, b) 
			given a and b may be palindromes, 
			this function returns the better one

	Computes:
		the best palindrome with factors in [a, b] (inclusive).
	"""
	best_palindrome, best_factors = None, []

	for x in _interval(*search_range):
		if best_palindrome and better(x*x, best_palindrome) != x*x:
			break

		for y in _interval(x, search_range[1]):
			product = x * y

			if best_palindrome and better(product, best_palindrome) != product:
				break

			if _is_palindrom(product):
				best_palindrome, best_factors = x * y, {x, y}
				break

	return best_palindrome, best_factors

def _interval(start, end, step = 1):
	"""
	Return the inclusive _interval from start to end. With the elements in 
	the order defined by the order of start and end.
	"""
	if start <= end:
		return range(start, end + step)
	elif start > end:
		return range(start, end - step, -step)

def _is_palindrom(number):
	as_str = str(number)
	return as_str == as_str[::-1]
