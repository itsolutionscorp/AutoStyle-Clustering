dic_transcript = {'G':'C', 'C':'G','T':'A','A':'U'}

def to_rna(dna):
    rna = ''
    for nu in dna:
        rna += transcript(nu)
    return rna
    
def transcript(nucleotide):
    return dic_transcript[nucleotide]
