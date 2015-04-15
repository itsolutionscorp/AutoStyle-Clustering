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
