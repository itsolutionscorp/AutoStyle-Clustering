drops = ((3,'Pling'), (5,'Plang'), (7,'Plong'))

def raindrops(n):
	"""Converts a number to a string according to the raindrop sounds.

	Note that we use the fact that if f is a factor of n, then n % f is
	zero (and hence false)
	"""
	speak = [s for f, s in drops if not n % f]
	return "".join(speak) if speak else str(n)
