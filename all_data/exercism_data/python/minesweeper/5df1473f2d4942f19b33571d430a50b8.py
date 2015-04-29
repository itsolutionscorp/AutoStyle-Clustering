from itertools import product

# determines whether or not board is an appropriate minesweeper board
# raises ValueError if board is malformed or otherwise improper
# returns a list of lines of the board if it is correctly formed
def test_validity(board):
	if board==None or not hasattr(board,"__iter__"):
		raise ValueError("board must be an iterable.")
	lines = list(board)
	if not all([ isinstance(line,str) for line in lines ]):
		raise ValueError("board must be an iterable of str's")
	if len(lines)<3:
		raise ValueError("board must be at least height 1.")
	if any([ len(line)!=len(lines[0]) for line in lines ]):
		raise ValueError("board must have all lines the same length.")
	if any([ len(line)<3 for line in lines ]):
		raise ValueError("board must be at least width 1.")
	if lines[0][0]!='+' or lines[0][-1]!='+' or lines[-1][0]!='+' or lines[-1][-1]!='+':
		raise ValueError("board's corners are malformed.")
	if any([ c!='-' for c in lines[0][1:-1] ]):
		raise ValueError("board's top is malformed.")
	if any([ c!='-' for c in lines[-1][1:-1] ]):
		raise ValueError("board's bottom is malformed.")
	if any([ lines[i][0]!='|' for i in range(1,len(lines)-1) ]):
		raise ValueError("board's left side is malformed.")
	if any([ lines[i][-1]!='|' for i in range(1,len(lines)-1) ]):
		raise ValueError("board's right side is malformed.")
	for i in range(1,len(lines)-1):
		for j in range(1,len(lines[0])-1):
			if lines[i][j] not in {' ','*'}:
				raise ValueError("Unknown character %s at position (%s,%s)."%(lines[i][j],i,j))
	return lines

# counts the mines adjacent (diagonally or orthogonally) to position (i,j)
# returns type str, returning a space in place of 0
def _count_mines(lines,i,j):
	answer=0
	for a,b in product([-1,0,1],repeat=2):
		if a==b==0:
			continue
		if lines[i+a][j+b]=='*':
			answer+=1
	if answer==0:
		return ' '
	return str(answer)

# calculates the number of mines adjacent (diagonally or orthogonally) to each square within the board
# mines must be an iterable of str's
def board(mines):
	lines = test_validity(mines)
	answer=[]
	for i in range(len(lines)):
		answer.append("")
		for j in range(len(lines[0])):
			if lines[i][j]==' ':
				answer[-1]+=_count_mines(lines,i,j)
			else:
				answer[-1]+=lines[i][j]
	return answer
