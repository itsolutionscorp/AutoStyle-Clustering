from math import sqrt

# returns a prime factorization of n in a list
def prime_factors(n):
	answer=[]
	divisor,remainder=2,n
	while divisor<=sqrt(remainder):
		if remainder%divisor==0:
			answer+=[divisor]
			remainder=remainder//divisor
		else:
			divisor+=1
	if remainder>1:
		answer+=[remainder]
	return answer
