def to_rna(dna):
    rna = ''
    for ch in dna:
	if ch == 'G':
	    rna += 'C'
	elif ch == 'C':
	    rna += 'G'
	elif ch == 'T':
	    rna += 'A'
	elif ch == 'A':
	    rna += 'U'
    return rna
