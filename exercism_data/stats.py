import os
for folder in os.listdir("./ruby"):
	single = 0
	double = 0
	other = 0
	path = "./ruby/" + folder + "/"
	for item in os.listdir(path):
		total = 0
		f = open(path + item)
		lines = f.readlines()
		for line in lines:
			if "def" in line:
				total+=1
		if total == 1:
			single+=1
		elif total == 2:
			double+=1
		else:
			other+=1
	print folder, ":  ", single, double, other
