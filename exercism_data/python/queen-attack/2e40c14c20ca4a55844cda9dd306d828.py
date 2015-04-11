def board(white_queen, black_queen):
	check_queens(white_queen, black_queen)

	(wx, wy), (bx, by) = white_queen, black_queen

	board = [['_'] * 8 for row in range(8)]
	board[wx][wy] = 'W'
	board[bx][by] = 'B'

	return ["".join(line) for line in board]

def can_attack(white_queen, black_queen):
	check_queens(white_queen, black_queen)

	(wx, wy), (bx, by) = white_queen, black_queen

	return wx == bx or wy == by or wx - wy == bx - by or wx + wy == bx + by

def check_queens(white_queen, black_queen):
	if white_queen == black_queen:
		raise ValueError("queens at the same place")

	cords = white_queen + black_queen

	if not all(0 <= cord < 8 for cord in cords):
		raise ValueError("queen not on the board")
