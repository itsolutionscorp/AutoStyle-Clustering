def slices(string,n):
	if n != int(n): 
		print "ERROR! N MUST BE AN INTEGER"
		raise ValueError
	if n > len(string):
		raise ValueError
	if n <= 0:
		raise ValueError
	if string != str(string):
		raise ValueError
	return [map(int,string[k:n+k]) for k in range(len(string)-n+1)]
