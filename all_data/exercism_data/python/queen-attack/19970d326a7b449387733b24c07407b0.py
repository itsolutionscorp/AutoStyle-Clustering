def board(w, b):

	#	raise an exception if the board positions are invalid
	can_attack(w, b)

	board = [(['_'] * 8) for n in range(8)]

	wy, wx = w
	board[wy][wx] = 'W'

	by, bx = b
	board[by][bx] = 'B'

	return [
		''.join(board[n]) for n in range(8)
	]


def can_attack(queen1, queen2):

	if queen1 == queen2:
		raise ValueError()

	x1, y1 = queen1
	x2, y2 = queen2

	if x1 > 7 or y1 > 7 or x2 > 7 or y2 > 7:
		raise ValueError()

	dx = abs(x1 - x2)
	dy = abs(y1 - y2)

	return dx == dy or dx == 0 or dy == 0
