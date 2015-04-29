DNA_TO_RNA = {'A': 'U',
	      'G': 'C',
	      'C': 'G',
	      'T': 'A'}

def to_rna(dna):
    rna = [DNA_TO_RNA[base] for base in dna]
    return ''.join(rna)
    
