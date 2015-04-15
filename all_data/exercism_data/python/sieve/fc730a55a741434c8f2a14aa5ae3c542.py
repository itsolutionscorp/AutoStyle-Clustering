def sieve(number):
	prime_list = []
	blacklist=[]
	for i in range(2, number):
		if not i in blacklist:
			prime_list.append(i)
		for p in range(i*2, number, i):
			blacklist.append(p)
	return prime_list
