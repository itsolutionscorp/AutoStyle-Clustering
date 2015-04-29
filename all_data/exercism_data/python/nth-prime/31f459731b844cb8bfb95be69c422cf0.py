def is_prime(n):
	if n ==2: return True
	m = int(n**.5)+1
	r = [0]*(m+1)
	for i in range(2,m+1):
		if n/i == float(n)/i:
			r[i] = 1
	return sum(r) == 0

def nth_prime(n):
	primelist = [2]
	k = 3
	l = 1
	while l < n:
		if is_prime(k):
			primelist.append(k)
		k+=1
		l = len(primelist)
	return primelist[-1]
