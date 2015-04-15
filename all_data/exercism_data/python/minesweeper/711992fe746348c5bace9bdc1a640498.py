def board(mines):
	if len(mines) < 3:
		raise ValueError("Malformed board")

	width, height = len(mines[0]), len(mines)

	if width < 3 or any(len(line) != width for line in mines):
		raise ValueError("Malformed board")

	if mines[0] != mines[-1]:
		raise ValueError("Top and bottom border not identical")

	if any(line[0] != line[-1] for line in mines[0]):
		raise ValueError("Left and right border not identical")

	if any(any(char not in "+- *|" for char in line) for line in mines):
		raise ValueError("invalid char")

	as_numbers = [[1 if field == '*' else 0 for field in line] for line in mines]

	updated = []
	for y, line in enumerate(mines):
		new_line = []
		for x, char in enumerate(line):
			if char == ' ':
				cnt = adj(as_numbers, y, x)
				if cnt != 0:
					new_line.append(str(cnt))
				else:
					new_line.append(' ')
			elif char == '*':
				new_line.append('*')
			else:
				new_line.append(char)
		updated.append("".join(new_line))

	return updated

def adj(mat, y, x):
	return (mat[y-1][x-1] + mat[y-1][x] + mat[y-1][x+1]
			+ mat[y][x-1] + mat[y][x+1]
			+ mat[y+1][x-1] + mat[y+1][x] + mat[y+1][x+1])
