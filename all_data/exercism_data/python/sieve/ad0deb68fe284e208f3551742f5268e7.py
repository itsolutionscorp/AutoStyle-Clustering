def find_next_prime(starting_at, markers):
	i = starting_at
	while i < len(markers):
		if not markers[i]:
			break
		i += 1
	return i

def mark_multiples_of(i, markers):
	j = i + i
	while j < len(markers):
		markers[j] = True
		j += i
	return markers

def sieve(n):
	markers = [False for i in xrange(n+1)]
	markers[0] = markers[1] = True
	i = 2
	while i <= n:
		i = find_next_prime(i, markers)
		if i > n:
			break
		markers = mark_multiples_of(i, markers)
		i += 1
	primes = [i for i in xrange(n+1) if not markers[i]]
	return primes
