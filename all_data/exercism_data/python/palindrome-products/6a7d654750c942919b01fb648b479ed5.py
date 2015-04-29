def palindrome(max_f, min_f, large):
	rang = range(min_f ** 2, max_f ** 2 + 1)
	if large:
		rang.reverse()
	for pal in rang:
		if is_palindrome(pal):
			for fact in range(min_f, int(pal ** 0.5)+1):
				if not pal % fact and min_f <= pal/fact <= max_f:
					return pal, (fact, pal/fact)
		
def largest_palindrome(max_factor, min_factor = 1):
	return palindrome(max_factor, min_factor, True)
	
def smallest_palindrome(max_factor, min_factor = 1):
	return palindrome(max_factor, min_factor, False)

def is_palindrome(n):
	s = str(n)
	return s == s[::-1]
