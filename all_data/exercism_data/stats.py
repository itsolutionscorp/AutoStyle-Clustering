import os
for folder in os.listdir("./python"):
	single = 0
	double = 0
	other = 0
	max_lines=0
	min_lines = 100000
	total_lines = 0
	path = "./python/" + folder + "/"
	assignments = os.listdir(path)
	for item in assignments:
		total = 0
		f = open(path + item)
		lines = f.readlines()
		length = len(lines)
		if length > max_lines:
			max_lines = length
		elif length < min_lines:
			min_lines = length
		total_lines += length
		for line in lines:
			if "def" in line:
				total += 1
		if total == 1:
			single += 1
		elif total == 2:
			double += 1
		else:
			other += 1
	print folder, ":  ", single, double, other, "..............", min_lines, max_lines, total_lines/len(assignments)
