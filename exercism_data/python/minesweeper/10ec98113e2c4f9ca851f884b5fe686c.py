neighbours = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]

def has_valid_border(input):

	#	check corners

	if (input[0][0] != '+' or input[0][-1] != '+' or
			input[-1][0] != '+' or input[-1][-1] != '+'):
		return False

	#	check up/down

	if any(n != '-' for n in input[0][1:-1]):
		return False
	if any(n != '-' for n in input[-1][1:-1]):
		return False

	return True

def get_result(input, x, y):

	char = input[y][x]
	if char == '*':
		return '*'
	if char != ' ':
		raise ValueError('invalid character on the board')

	summa = 0
	for dx, dy in neighbours:
		if input[y+dy][x+dx] == '*':
			summa += 1
	return str(summa) if summa else ' '

def board(input):

	width = len(input[0])
	height = len(input)

	#	make sure all rows have the same lengths
	if any(len(input[n]) != width for n in range(height)):
		raise ValueError('non-uniform widths')

	if not has_valid_border(input):
		raise ValueError('invalid character in the border')

	rows = []
	for y in xrange(1, height - 1):
		row = [
			get_result(input, x, y)
			for x in xrange(1, width - 1)
		]
		rows.append('|%s|' % ''.join(row))

	return [input[0]] + rows + [input[-1]]
