# casts x as a pair (tuple) of ints in range(8), if this can be done, raises ValueError if not.
def _valid_pair(x):
	if not hasattr(x,'__iter__'):
		raise ValueError("Parameter must be a pair.")
	pair=tuple(x)
	if len(pair)!=2:
		raise ValueError("Parameter must have exactly two components.")
	intpair=(int(pair[0]),int(pair[1]))
	if (isinstance(pair[0],float) and intpair[0]!=pair[0]) or (isinstance(pair[1],float) and intpair[1]!=pair[1]):
		raise ValueError("Parameter's components must be integers.")
	if not (0<=intpair[0]<8) or not (0<=intpair[1]<8):
		raise ValueError("Parameter must be a valid position on a chess board.")
	return intpair

# determines whether or not two queens at the indicated positions could attack each other
def can_attack(white,black):
	wpos=_valid_pair(white)
	bpos=_valid_pair(black)
	if wpos==bpos:
		raise ValueError("Two pieces cannot be at the same location.")
	return wpos[0]==bpos[0] or wpos[1]==bpos[1] or wpos[0]-bpos[0]==wpos[1]-bpos[1] or wpos[0]-bpos[0]==bpos[1]-wpos[1]

# returns a chess board with queens at the indicated positions
def board(white,black):
	wpos=_valid_pair(white)
	bpos=_valid_pair(black)
	if wpos==bpos:
		raise ValueError("Two pieces cannot be at the same location.")
	answer=[]
	for i in range(8):
		answer.append('')
		for j in range(8):
			if (i,j)==wpos:
				answer[-1]+='W'
			elif (i,j)==bpos:
				answer[-1]+='B'
			else:
				answer[-1]+='0'
	return answer
