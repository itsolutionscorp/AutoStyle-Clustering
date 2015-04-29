#!/usr/bin/env python
 # -*- coding: utf-8 -*-


def sieve(n):
	if not isinstance(n, int):
		raise ValueError("n must be an integer")
		
	not_primes = [j for i in range(2,n+1) for j in range(i+1,n+1) if i != j and j%i == 0]
	return [u for u in range(2,n+1) if u not in not_primes]
