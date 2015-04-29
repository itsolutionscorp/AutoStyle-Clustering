def board(w, b):
	# let's validate
	if w == b: 
		raise ValueError('pieces are in the same position')
		
	if not all(isinstance(d,(int, long)) for d in w+b): 
		raise ValueError('positions are not all integers')
		
	if not all(d>=0 and d<8 for d in w+b): 
		raise ValueError('positions are not all valid locations')
	
	# looks good
	outp = [['_' for i in range(8)] for j in range(8)]
	outp[w[0]][w[1]] = 'W'
	outp[b[0]][b[1]] = 'B'
	return [''.join(line) for line in outp]
	
def can_attack(w, b):
	# let's validate
	if w == b: 
		raise ValueError('pieces are in the same position')
		
	if not all(isinstance(d,(int, long)) for d in w+b): 
		raise ValueError('positions are not all integers')
		
	if not all(d>=0 and d<8 for d in w+b): 
		raise ValueError('positions are not all valid locations')
	
	# looks good
	return w[0]==b[0] or w[1]==b[1] or abs(w[0]-b[0]) == abs(w[1]-b[1])
