import re
ends = re.compile('\+-*\+')
middles = re.compile('\|[ *]+\|')

def board(board):
	height, width = validate_board(board)

	for i in range(1, height - 1):
		for j in range(1, width - 1):
			count = 0
			for k in range(i - 1, i + 2):
				for z in range(j - 1, j + 2):
					if board[k][z] == '*':
						count += 1
			if count > 0 and board[i][j] != '*':
				board[i] = board[i][:j] + str(count) + board[i][j+1:]

	return board


def validate_board(board):
	if not board:
		raise ValueError('Board is None or empty.')

	height = len(board)
	width = len(board[0])

	if not all(len(line) == width for line in board):
		raise ValueError('Lines have different length.')

	if not ends.fullmatch(board[0]) or not ends.fullmatch(board[-1]):
		raise ValueError('Borders are incorrectly formatted.')
	if not all(bool(middles.fullmatch(line)) for line in board[1:-1]):
		raise ValueError('Borders are incorrectly formatted.')

	return height, width
