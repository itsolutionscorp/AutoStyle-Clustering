def board(white_coord, black_coord):
	_ensure_coords(white_coord, black_coord)
	board = [['_']*8 for _ in range(8)]
	board[white_coord[0]][white_coord[1]] = 'W'
	board[black_coord[0]][black_coord[1]] = 'B'
	return ["".join(line) for line in board]

def can_attack(white_coord, black_coord):
	_ensure_coords(white_coord, black_coord)
	return any([white_coord[0] == black_coord[0],
				white_coord[1] == black_coord[1],
				white_coord[0] + white_coord[1] == black_coord[0] + black_coord[1],
				white_coord[0] - white_coord[1] == black_coord[0] - black_coord[1]])

def _ensure_coords(white_coord, black_coord):
	if white_coord == black_coord:
		raise ValueError('Queens can not be in the same position')
	if any(i < 0 or i > 7 for i in white_coord + black_coord):
		raise ValueError('Coordinates out of range')
