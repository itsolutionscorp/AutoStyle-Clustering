raw_nums = [
' _    ', #' _  _     _  _  _  _  _ ',
'| |  |', #' _| _||_||_ |_   ||_||_|',
'|_|  |', #'|_  _|  | _||_|  ||_| _|',
'      ', #'                        '
]

max_num = 1

num_grid = []
for ix in range(max_num+1):
	num_grid.append( [ s[ix*3:(ix+1)*3] for s in raw_nums  ] )

def cmp_str(l1, l2):
	for ix in range(4):
		if l1[ix] != l2[ix]:
			return False
	return True

def number(l):
	if len(l) != 4:
		raise ValueError('not enough lines')
	for line in l:
		if len(line) != 3:
			raise ValueError('Line too short')

	for ix in range(max_num+1):
		if cmp_str(l, num_grid[ix]):
			return str(ix)
	return '?'

def grid(num):
	num = int(num)
	if num > max_num:
		raise ValueError('not implemented yet')
	return num_grid[num]
