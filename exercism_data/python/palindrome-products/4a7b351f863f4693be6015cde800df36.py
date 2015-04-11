mem = {}

def largest_palindrome(max_factor, min_factor=0):
	return palindrome_in_range(max_factor, min_factor)

def smallest_palindrome(max_factor, min_factor=0):
	return palindrome_in_range(max_factor, min_factor, lambda x,y: x<y)

def palindrome_in_range(max_factor, min_factor=0, comp=lambda x,y: x>y):
	factors = []
	current = -1
	for i in range(min_factor, max_factor + 1):
		for j in range(i, max_factor + 1):
			res = i*j
			if (current == -1 or comp(res, current)) and is_palindrome(res):
				current = res
				factors = [i, j]

	return current, factors	

def is_palindrome(num):
	string = str(num)
	return string == string[::-1]
