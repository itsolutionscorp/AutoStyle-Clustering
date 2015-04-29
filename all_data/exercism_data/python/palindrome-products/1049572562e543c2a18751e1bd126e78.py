def is_palindrome(n):
	return (str(n) == str(n)[::-1])


def palindromes(min_factor, max_factor):
	return (
		(i * j, {i, j})
		for i in xrange(min_factor, max_factor)
		for j in xrange(i, max_factor + 1)
		if is_palindrome(i * j)
	)


def smallest_palindrome(max_factor, min_factor = 0):
	return min(palindromes(min_factor, max_factor))


def largest_palindrome(max_factor, min_factor = 0):
	return max(palindromes(min_factor, max_factor))
