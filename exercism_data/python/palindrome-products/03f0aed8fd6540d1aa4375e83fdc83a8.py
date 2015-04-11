from __future__ import division

def smallest_palindrome(max_factor, min_factor=0):
	best_palindrome = max_factor * max_factor
	best_factors = set()

	for x in range(min_factor, max_factor + 1):
		for y in range(x, best_palindrome // x + 1):
			if is_palindromic(x * y) and x * y < best_palindrome:
				best_palindrome = x * y
				best_factors = {x, y}
				break
		if x * x > best_palindrome:
			break

	return best_palindrome, best_factors


def largest_palindrome(max_factor, min_factor=0):
	best_palindrome = min_factor * min_factor
	best_factors = set()

	for x in range(max_factor, min_factor - 1, -1):
		for y in range(x, best_palindrome // x - 1, -1):
			if is_palindromic(x * y) and x * y > best_palindrome:
				best_palindrome = x * y
				best_factors = {x, y}
				break
		if x * x < best_palindrome:
			break

	return best_palindrome, best_factors


def is_palindromic(number):
	as_str = str(number)
	return as_str == as_str[::-1]
