def tointlist(intstr):
	return [int(x) for x in intstr]

def slices(n, length):
	if length > len(n) or length <= 0:
		raise ValueError
	return [tointlist(n[x:x+length]) for x in range(len(n)-length+1)]
