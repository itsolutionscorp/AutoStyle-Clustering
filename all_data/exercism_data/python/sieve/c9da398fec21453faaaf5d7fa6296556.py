def sieve(num):
	available = range(2,num+1)
	out = []
	while len(available):
		cur = available.pop(0)
		out.append(cur)
		available = [i for i in available if i % cur]
	return out
