_ocr_map = {
	'0': [" _ ",
	      "| |",
	      "|_|",
	      "   "],
	'1': ["   ",
	      "  |",
	      "  |",
	      "   "]
}

def _validate_grid(grid):
	wrong_num_rows = len(grid) != 4
	wrong_row_size = len(filter(lambda x: len(x) != 3, grid)) > 0
	if wrong_num_rows or wrong_row_size:
		raise ValueError('Ill-formed grid')

def number(grid):
	_validate_grid(grid)
	for k, v in _ocr_map.iteritems():
		if v == grid:
			return k
	return '?'

def grid(number):
	if number not in _ocr_map:
		raise ValueError('Unknown digit')
	return _ocr_map[number]
