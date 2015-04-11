'''hamming.py
	created 25 Sept 2014
	by @jestuber
	'''

def hamming(strand1,strand2):
	
	if not strand1 or strand1.isspace():
		return 0

	if len(strand1) > len(strand2):
		#add some nonsense nucleotides to make lengths even
		strand2 += (len(strand1) - len(strand2)) * 'X' 
	elif len(strand2) > len(strand1):
		strand1 += (len(strand2) - len(strand1)) * 'X'

	distance = 0
	for i in range(0,len(strand1)):
		if strand1[i] != strand2[i]:
			distance += 1

	return distance
