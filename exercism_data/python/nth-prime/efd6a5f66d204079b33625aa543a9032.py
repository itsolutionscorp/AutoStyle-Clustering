from math import floor, sqrt
from urllib.request import urlopen
from re import search

def is_prime(n):
	"""Determines whether or not n is prime."""
	for i in range(2,floor(sqrt(n))+1):
		if n%i == 0:
			return False
	return True

def straightforward_nth_prime(n):
	"""Returns the nth prime number."""
	primes_seen = 0
	index = 2
	max_index = 2**n
	while index <= max_index:
		if is_prime(index):
			primes_seen += 1
		if primes_seen == n:
			return index
		index += 1
	return None

def snarky_nth_prime(n):
	"""Returns the nth prime number."""
	nth_prime_page = str(urlopen("https://primes.utm.edu/nthprime/index.php?n=%s"%n).read()).replace(',','')
	result = search("The \\d*[a-z]{2} prime is (\\d*)",nth_prime_page)
	if result:
		return int(result.group(1))
	return None

def nth_prime(n):
	"""Returns the nth prime number."""
	return straightforward_nth_prime(n)
