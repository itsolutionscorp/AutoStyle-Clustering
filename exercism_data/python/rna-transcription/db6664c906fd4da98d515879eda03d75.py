def to_rna(dna_strand):
    trans_nucleotides = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna_complement = ''
    
    for nucleotide in dna_strand.upper():
        try:
            rna_complement += trans_nucleotides[nucleotide]
        except:
            rna_complement = 'ERR: invalid dna strand'

    return rna_complement
