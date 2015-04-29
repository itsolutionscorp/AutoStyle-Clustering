def board(white, black):
	validate(white, black)

	board = [['0']*8 for i in range(8)]
	board[white[0]][white[1]] = 'W'
	board[black[0]][black[1]] = 'B'
	return [''.join(line) for line in board]

def can_attack(white, black):
	validate(white, black)

	if any(v in black for v in white):
		return True

	if abs(white[0] - black[0]) == abs(white[1] - black[1]):
		return True

	return False

def validate(white, black):
	if white == black:
		raise ValueError('Queens overlap.')

	if not all(v >= 0 and v < 8 for queen in (white, black) for v in queen):
		raise ValueError('Queens are out of bounds.')
