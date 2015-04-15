def to_rna(seq):
	trans= {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	a=""
	
	#####Break String into list	
	for letter in seq:
		a+=(trans[letter])
	return (a)
