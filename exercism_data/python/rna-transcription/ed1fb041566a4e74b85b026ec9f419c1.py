def to_rna(inPut):
	seq=[]
	for DNA in inPut:
		if(DNA=='G'):
			seq.append('C')
		if(DNA=='C'):
			seq.append('G')
		if(DNA=='A'):
			seq.append('U')
		if(DNA=='T'):
			seq.append('A')
	return "".join(seq)
