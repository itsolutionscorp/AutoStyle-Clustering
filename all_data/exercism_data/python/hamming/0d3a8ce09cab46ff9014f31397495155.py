def distance(s1,s2):
	return [n[0]==n[1] for n in zip(s1,s2)].count(False)
