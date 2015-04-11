def to_rna(dna):
    conversions = {
                        'G' : 'C',
                        'C' : 'G',
                        'T' : 'A',
                        'A' : 'U'
                        }
    rna = ""
    for nucleotide in dna:
        rna += conversions[nucleotide]
    return rna
