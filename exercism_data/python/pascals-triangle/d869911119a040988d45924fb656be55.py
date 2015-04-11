def row(n):
	if n < 0:
		raise ValueError("Undefined for negative values!")
	if n == 0:
		return '1'
	prev_vals = row(n - 1).split(' ')
	vals = ['1'] + [str(int(a)+int(b)) for a,b in zip(prev_vals, prev_vals[1:])] + ['1']
	return ' '.join(vals)

def triangle(n):
	return [row(i) for i in xrange(0, n + 1)]

def is_triangle(inp):
	return triangle(len(inp) - 1) == inp
