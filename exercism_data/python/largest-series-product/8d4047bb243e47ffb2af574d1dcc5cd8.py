def _slicer(s, size):
	'''
	Slice string into overlapping chunks of given size
	_slicer('123456', 2) -> ['12', '23', '34', '45', '56']
	'''

	newsize = len(s) - size + 1
	return (s[i:i+size] for i in xrange(newsize))

def _tointlist(n):
	'''
	Convert given number (string) to list of digits (int)
	'1234' -> [1,2,3,4]
	'''

	return [int(x) for x in n]

def largest_product(n, size):
	if size > len(n):
		raise ValueError
	if not n:
		return 1
	return max(reduce(lambda x,y:x*y, arr) for arr in slices(n, size))

def slices(n, size):
	if size > len(n):
		raise ValueError
	return [_tointlist(x) for x in _slicer(n, size)]
