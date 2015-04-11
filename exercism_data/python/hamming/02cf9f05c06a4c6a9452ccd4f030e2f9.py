'''
Hamming Distance
'''

def distance(A, B):
	return sum([B[i] != x for i,x in enumerate(A)])
