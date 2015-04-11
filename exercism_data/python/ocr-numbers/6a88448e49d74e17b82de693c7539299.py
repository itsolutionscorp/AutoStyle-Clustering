DIGITS_TO_GRID = [
	[" _ ",
     "| |",
     "|_|",
     "   "],
	["   ",
	 "  |",
	 "  |",
	 "   "]
]

GRID_WIDTH, GRID_HEIGHT = (3, 4)

def _validate_grid(grid):
	wrong_num_rows = len(grid) != GRID_HEIGHT
	wrong_row_size = any(len(row) != GRID_WIDTH for row in grid)
	if wrong_num_rows or wrong_row_size:
		raise ValueError('Ill-formed grid')

def number(grid):
	_validate_grid(grid)
	try:
		return str(DIGITS_TO_GRID.index(grid))
	except ValueError:
		return '?'

def grid(number):
	number = int(number)
	if not (0 <= number < len(DIGITS_TO_GRID)):
		raise ValueError('Unknown digit')
	return DIGITS_TO_GRID[number]
