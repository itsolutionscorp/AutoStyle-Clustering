import collections




def count(target,element):
	s=('A','T','C','G','U')
	if element not in s:
		raise ValueError , "bad arguement"
	else:
		return collections.Counter(target)[element]

def nucleotide_counts(string):
	result= collections.Counter(string)
	if 'A' not in result.keys():
		result['A']=0
	if 'T' not in result.keys():
		result['T']=0
	if 'G' not in result.keys():
		result['G']=0
	if 'C' not in result.keys():
		result['C']=0
	#print result
	return result
