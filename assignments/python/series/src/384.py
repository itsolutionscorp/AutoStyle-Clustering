def slices(string,n):
	if not n in range(1,len(string)+1):
		raise ValueError('invalid length')
	cutstring = map(int,string)
	return [cutstring[i:i+n] for i in range(0,len(cutstring) - n + 1)]
