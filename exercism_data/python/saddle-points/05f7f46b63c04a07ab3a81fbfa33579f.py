def saddle_points(rows):
	if any(len(rows[0]) != len(row) for row in rows):
		raise ValueError() 
	
	columns = zip(*rows)
	points = set()
	for r, row in enumerate(rows):
		high = max(row)
		for c, value in enumerate(row):
			if value == high and value == min(columns[c]):
				points.add((r, c))
				
	return points
