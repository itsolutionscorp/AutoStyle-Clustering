def slices(s, l):
	"""
		Find all consecutive series of length l in s.
	"""
	n = [ int(x) for x in s ]
	if not 1 <= l <= len(s):
		raise ValueError("Invalid slice length")
	return [ n[i:i+l] for i in range(len(s)-l+1)] 		
