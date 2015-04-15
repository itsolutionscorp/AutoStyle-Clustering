import operator

mem = {}

def largest_palindrome(max_factor, min_factor=0):
	return palindrome_in_range(max_factor, min_factor)

def smallest_palindrome(max_factor, min_factor=0):
	return palindrome_in_range(max_factor, min_factor, operator.lt)

def palindrome_in_range(max_factor, min_factor=0, comp=operator.gt):
	factors = []
	current = None
	for i in range(min_factor, max_factor + 1):
		for j in range(i, max_factor + 1):
			res = i*j
			if (not current or comp(res, current)) and is_palindrome(res):
				current = res
				factors = [i, j]

	return current, factors	

def is_palindrome(num):
	string = str(num)
	return string == string[::-1]
