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
