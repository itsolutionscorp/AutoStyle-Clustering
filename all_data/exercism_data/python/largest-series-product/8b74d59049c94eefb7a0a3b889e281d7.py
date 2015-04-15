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

def largest_product(string,n):
	ints = map(int,string)
	l = len(ints) - n +1
	return max([reduce(lambda x,y: x*y,ints[k:n+k],1) for k in range(l)])
