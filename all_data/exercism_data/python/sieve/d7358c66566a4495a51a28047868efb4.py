import math

def isPrime(x):
	for n in range(3,int(math.sqrt(x))+1, 2):
		if x%n==0:
			return False
	return True
	
def sieve(num):
	"""
	This solution no longer feels like a 'sieve' solution - I'm not eliminating things forward by looking forward at its multiples.
	"""
	answers = [2]
	nums_to_test = range(3,num+1,2)
	return [2]+ list(filter(isPrime,nums_to_test))
