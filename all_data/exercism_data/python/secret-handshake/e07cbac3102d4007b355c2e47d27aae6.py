RING = (
	(1, 'wink'),
	(2, 'double blink'),
	(4, 'close your eyes'),
	(8, 'jump'),
)

def handshake(num):
	if isinstance(num, basestring): 
		try:
			num = int(num, 2)
		except:
			num = 0
	
	if num < 0: return []
	
	motions = [t[1] for t in RING if num & t[0]]
	if num & 16: motions.reverse()
	return motions

def code(motions):
	motions = motions[:]
	num = 0 
	
	ringlet = [t[1] for t in RING]
	try:
		if ringlet.index(motions[0]) > ringlet.index(motions[1]):
			num = 16
	except:
		pass # item was not found, we'll handle that in the return statement
		
	for t in RING:
		if t[1] in motions: 
			num += t[0]
			motions.remove(t[1])
	
	return "{0:b}".format(num if not motions else 0)
