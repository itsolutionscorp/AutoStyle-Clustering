import functools

def board(board):
	# validate board size and borders
	if len(board) == 0:
		raise ValueError("Empty board")
	if len(set(map(len, board))) != 1:
		raise ValueError("All lines must be of equal positive length")
	hor_border = '+' + '-'*(len(board[0]) - 2) + '+'
	if board[0] != hor_border or board[-1] != hor_border or not all(line[0] == line[-1] == '|' for line in board[1:-1]):
		raise ValueError("Have to enforce borders for some reason. Sorry.")

	# collect mine counts into table of integers
	counts = [[0]*(len(board[0])-2) for i in range(len(board) - 2)]
	for y, line in enumerate(board[1:-1]):
		for x, char in enumerate(line[1:-1]):
			if char == '*':
				counts[y][x] = -1
				for i in range(x-1, x+2):
					for j in range(y-1, y+2):
						try:
							if i >= 0 and j >= 0 and counts[j][i] >= 0:
								counts[j][i] += 1
						except IndexError:
							pass
			elif char != ' ':
				raise ValueError("Unknown character in a field: "+char)

	# convert table of integers into strings
	result = ['|' + "".join(_subs[i] for i in line) + '|' for line in counts]
	result.append(hor_border)
	result[:0] = [hor_border]
	return result

# helper to convert integer to character on the field
_subs = {i:str(i) for i in range(1, 10)}
_subs[-1] = '*'
_subs[0] = ' '
