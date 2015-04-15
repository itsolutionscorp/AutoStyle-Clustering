import re

def board(in_board):
	# let's validate the board
	if any(len(line) != len(in_board[0]) for line in in_board): 
		raise ValueError('invalid board: lengths not equal')
		
	if not re.match(r'^\+\-+\+$', in_board[0]): 
		raise ValueError('invalid board: top border broken')
		
	if not re.match(r'^\+\-+\+$', in_board[-1]): 
		raise ValueError('invalid board: bottom border broken')
		
	for line in in_board[1:-1]:
		if not re.match(r'^\|[ *]+\|$', line): 
			raise ValueError('invalid board: left/right border broken or invalid token')
	
	# looks good, let's do this!
	out_board = [list(line) for line in in_board]
	for r in xrange(len(out_board)):
		for c in xrange(len(out_board[r])):
			if out_board[r][c] == '*':
				for i in xrange(-1, 2):
					for j in xrange(-1, 2):
						out_board[r+i][c+j] = adjust(out_board[r+i][c+j])
	
	return [''.join(line) for line in out_board]

def adjust(c):
	if c == ' ': 
		c = '1'
	elif c.isdigit(): 
		c = str(int(c) + 1)
	return c
