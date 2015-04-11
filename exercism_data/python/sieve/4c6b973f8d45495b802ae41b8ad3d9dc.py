def sieve(limit):
	x = list(range(2,limit+1))
	for num in x:
		for next_num in x:
			if num != next_num and next_num%num == 0:
				x.remove(next_num)
	return x
