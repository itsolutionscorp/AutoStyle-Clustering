def slices(string,n):
	if n > len(string) or n == 0:
		raise ValueError()
	else:
		sliced = []
		s = 0
		while s <= len(string)-n:
			item =  [int(i) for i in list(string[s:s+n])]
			sliced.append(item)
			s +=1	
		return sliced
