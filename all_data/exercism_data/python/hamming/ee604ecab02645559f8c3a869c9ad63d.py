#/usr/bin/env python
def distance(xx,yy):
	result = len(xx)
	for nn in range(len(xx)):
		if xx[nn] == yy[nn]:
			result -= 1
	return result
