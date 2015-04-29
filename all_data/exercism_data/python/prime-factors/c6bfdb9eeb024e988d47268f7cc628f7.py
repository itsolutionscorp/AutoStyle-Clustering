	
def prime_factors(num):
	res = []
	prime = 2
	while True:
		if num==1:
		 	return res
		if is_prime(num):
			return res + [num]
		if num%prime == 0:
			res.append(prime)
			num = num/prime
		else:
			prime = next_prime(prime)
		

def next_prime(prime):
	while True:
		prime += 1
		is_it_prime = is_prime(prime)
		if is_it_prime:
			return prime

def is_prime(num):
	x = (num/2)+1
	i = 2
	while i <= x:
		if num%i==0:
			return False
		i+=1
	return True
	
