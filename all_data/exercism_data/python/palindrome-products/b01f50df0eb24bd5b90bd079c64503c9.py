from __future__ import division

def largest_palindrome(max_factor, min_factor=0):
	return palindrome_in_range((max_factor, min_factor), max)

def smallest_palindrome(max_factor, min_factor=0):
	return palindrome_in_range((min_factor, max_factor), min)

def palindrome_in_range(search_range, better):
	"""
	Given:
		a search_range (a, b) for the factors,
		a function better(a, b) 
			which, given a and b may be palindromes, 
			returns which of those would be better

	Computes:
		the best palindrome with factors in [a, b] (inclusive).
	"""
	not_better = inverse(better)
	search_range = (better(search_range[0], search_range[1]), 
					not_better(search_range[0], search_range[1]))
	best_palindrome = search_range[1]**2
	best_factors = {search_range[1]}

	for x in interval(*search_range):
		for y in interval(x, best_palindrome // x):
			if is_palindromic(x * y) and best_palindrome != better(best_palindrome, x * y):
				best_palindrome = x * y
				best_factors = {x, y}
				break

		if better(x * x, best_palindrome) != x * x:
			break

	return best_palindrome, best_factors

def inverse(better):
	def not_better(a, b):
		if better(a, b) == a:
			return b
		else:
			return a
	return not_better

def interval(start, end, step = 1):
	"""
	Return the inclusive interval from start to end. With the elements in 
	the order defined by the order of start and end.
	"""
	if start <= end:
		return range(start, end + step)
	elif start > end:
		return range(start, end - step, -step)

def is_palindromic(number):
	as_str = str(number)
	return as_str == as_str[::-1]
