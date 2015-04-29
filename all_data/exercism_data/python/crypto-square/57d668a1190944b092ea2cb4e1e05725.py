import math

def _make_square(s, n):
	rows = []
	for j in range(n):
		rows.append([''] * n)
	j = -1
	for i, c in enumerate(s):
		if i % n == 0:
			j += 1
		rows[j][i % n] = c
	return rows

def encode(s):
	s = "".join(c for c in s.lower() if c.isdigit() or c.isalpha())
	ncols = int(math.ceil(math.sqrt(len(s))))
	rows = _make_square(s, ncols)
	output = []
	j = 0
	for i in range(ncols):
		for row in rows:
			if row[i]:
				output.append(row[i])
				j += 1
				if j % 5 == 0:
					output.append(' ')
	return "".join(output).rstrip()


def decode(s):
	s = s.replace(' ','')
	ncols = int(math.ceil(math.sqrt(len(s))))
	rows = _make_square(s, ncols)
	s_i = iter(s)
	t = ""
	for i in range(ncols):
		for j in range(len(rows)):
			if rows[j][i]:
				t += next(s_i)
			else:
				t += ' '
	out = []
	for i in range(ncols):
		for j in range(ncols):
			out.append(t[j * ncols + i])
	return "".join(out).rstrip()
