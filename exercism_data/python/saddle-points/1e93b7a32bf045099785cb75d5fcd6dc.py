from collections import defaultdict

def saddle_points(m):

	if not m:
		return set()

	rows = m

	width = len(rows[0])
	height = len(rows)

	if any(len(row) != width for row in rows):
		raise ValueError('Malformed matrix')

	cols = [
	    [rows[i][j] for i in range(height)]
	    for j in range(width)
	]

	#	rows

	row_max = set()
	for row_id, row in enumerate(rows):
		max_dict = defaultdict(set)
		max_key = 0
		for index, key in enumerate(row):
			max_dict[key].add(index)
			if key > max_key:
				max_key = key

		for col_id in max_dict[max_key]:
			row_max.add((row_id, col_id))

	#	cols

	col_min = set()
	for col_id, col in enumerate(cols):
		min_dict = defaultdict(set)
		min_key = 10
		for index, key in enumerate(col):
			min_dict[key].add(index)
			if key < min_key:
				min_key = key

		for row_id in min_dict[min_key]:
			col_min.add((row_id, col_id))


	return row_max & col_min
