# got ideas from @tdunn19 on this one.
def to_rna(s):
	key = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
	
	rna = ''.join(key[base] for base in s)
	
	return rna
