ROW = 8
COL = 8

def board(rcW, rcB):
	check(rcW, rcB)
	brd = []
	for i in range(ROW):
		brd.append(['_'] * COL)
	i,j = rcW
	brd[i][j] = 'W'
	i,j = rcB
	brd[i][j] = 'B'
	for i in range(COL):
		brd[i] = ''.join(brd[i])
	return brd
			
def can_attack(rcW, rcB):
	check(rcW, rcB)
	r = rcW[0] - rcB[0]
	c = rcW[1] - rcB[1]
	if r == 0 or c == 0:
		return True
	if abs(r) == abs(c):
		return True
	return False

def check(rcW, rcB):
	if rcW == rcB:
		raise ValueError('Queens cannot occupy same space.')
	for n in rcW + rcB:
		if n < 0 or n >= ROW:
			raise ValueError('Invalid coordinates.')
