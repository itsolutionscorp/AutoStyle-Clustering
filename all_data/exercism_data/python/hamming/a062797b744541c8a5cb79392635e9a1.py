def distance(a, b):
    dist = 0
    if len(a) != len(b):
	print "Hamming distance only valid for sequences of equal length!"
	return -1
    dist = 0
    for i in range(0, len(a)):
	if a[i] != b[i]:
	    dist+=1
    return dist
