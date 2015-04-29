import itertools

def on_square(n):
	return 2**(n-1)

def total_after(n):
	return sum(itertools.islice(_powers_of_2(), n))

def _powers_of_2():
	i = 1
	while True:
		yield i
		i <<= 1 # elitish way to double i
