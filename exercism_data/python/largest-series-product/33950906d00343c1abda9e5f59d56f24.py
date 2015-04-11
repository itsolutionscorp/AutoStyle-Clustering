#!/usr/bin/python -tt

def slices(series,l):
	if l > len(series):
		raise ValueError('Requested series is longer than string')
	if l == 0:
		raise ValueError('Requested series has zero length')
		
	out=[]
	
	for n in range(0,len(series)-l+1):
		out.append([int(x) for x in series[n:n+l]])
		
	return out
	
def prod(iList):
	p=1
	for n in iList:
		p*=n
	return p	

def largest_product(series,l):
	if len(series)==0:
		return 1
	return max(prod(x) for x in slices(series,l))
