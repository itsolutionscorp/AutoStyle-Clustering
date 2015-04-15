

def distance(sa, sb):
	place = 0
	hd = 0
	while place < len(sa):
		if sa[place] != sb[place]:
			hd += 1
			place += 1
		else:
			place += 1
	return hd
