valid_chars = '|_ '

grids = {'0': [' _ ', '| |', '|_|', '   '], '1': ['   ', '  |', '  |', '   '], '2': [' _ ', '  _|', ' |_', '   '],
'3': [' _ ', ' _|', ' _|', '   '], '4': ['   ', '|_|', '  |', '   '], '5': [' _ ', '|_ ', ' _|', '   '], '6': [' _ ', '|_ ', '|_|', '   '],
'7': [' _ ', '  |', '  |', '   '], '8': [' _ ', '|_|', '|_|', '   '], '9': [' _ ', '|_|', ' _|', '   ']}

numbers = {''.join(v): k for k, v in grids.items()}

def number(grid):
	if len(grid) != 4:
		raise ValueError('Invalid number of rows.')

	if any(len(line) != 3 for line in grid):
		raise ValueError('Invalid row length.')

	if any(c not in valid_chars for line in grid for c in line):
		return '?'

	grid = ''.join(grid)
	if grid in numbers:
		return numbers[grid]
	else:
		return '?'

def grid(number):
	if number in grids:
		return grids[number]
	else:
		raise ValueError('No grid representation for the number {}.'.format(number))
