drops = {3: 'Pling', 5: 'Plang', 7: 'Plong'}

def raindrops(n):
	st = [s for f, s in drops.items() if not n % f]
	return "".join(st) if st else str(n)
