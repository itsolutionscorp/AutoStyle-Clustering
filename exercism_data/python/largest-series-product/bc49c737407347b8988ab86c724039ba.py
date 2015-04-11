def largest_product(s, n):
	if n > len(s) :
		raise ValueError("Too big : %r > %r, you deserve whatever you get ;-)" % (n, s))
	elif n == 0:
		return 1
	else:
		return max([reduce(lambda a,b: a*b,x,1) for x in slices(s, n)])


#re-use of series solution for finding all series of x digits in a string
def slices(l, n):
	if n > len(l) or n == 0:
		raise ValueError("Too big : %r > %r, you deserve whatever you get ;-)" % (n, l))
	else:
		return getSlice(l,n,list())
    

def getSlice(l,n,r):
	if n == len(l):
		r.append([int(x) for x in l])
		return r
	else:
		r.append([ int(x) for x in l[:n]])
		return getSlice(l[1:], n, r)
