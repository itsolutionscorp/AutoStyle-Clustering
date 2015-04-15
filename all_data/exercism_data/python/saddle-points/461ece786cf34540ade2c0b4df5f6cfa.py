def saddle_points(rows):
	if not rows:
		return set(rows)
	first = len(rows[0])
	res = []
	columns = []
	for i in range(len(rows[0])):
		columns.append([row[i] for row in rows if len(row) == first])
	for a in range(len(rows)):
		row = rows[a]
		if len(row) != first:
			raise ValueError("rows are not even")
		for b in range(len(row)):
			column = columns[b]
			if row[b] == max(row) and row[b] == min(column):
				res.append((a,b))
	return set(res)
