

def sieve(n):
	possibilities = range(2,n+1)
	for number in possibilities:
		for num in possibilities:
			if num % number == 0 and num != number:
				possibilities.remove(num)
	return possibilities
