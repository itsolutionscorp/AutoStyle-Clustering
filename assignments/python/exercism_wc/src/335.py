def word_count(input):
	wl = {}	
	input = input.split()
	for x in input:
		if x not in wl:
			wl[x] = 1
		else:
			wl[x] += 1
	print wl
	return wl
