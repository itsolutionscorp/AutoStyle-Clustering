#!/usr/bin/python -tt
import math
	
def sieve(num):
	sieve=[True for i in range(0,num+1)]
	sieve[1] = False
	sieve[0] = False
	
	for x in range(2,int(math.floor(math.sqrt(num)))+1):
		if sieve[x]:
			for n in [x**2 + i*x for i in range(0,num/x) if x**2+i*x < num]:
				sieve[n] = False
				
	return [n for n in range(1,num) if sieve[n]]
