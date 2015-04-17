def slices(numbers, r):
	text = []
	for i in list(numbers): text.append(int(i))	# string to list of numbers
	if r > len(text): raise ValueError 
	if r <= 0: raise ValueError
	results = []
	presults =  []
	for i in range(0, (len(text) + 1 - r)):
		for x in range(i, i + r):
			presults.append(text[x])
		results.append(presults)
		presults = []
	return results
