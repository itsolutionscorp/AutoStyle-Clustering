# Feedback http://exercism.io/submissions/988d1e599b595fa641a10ee5

def to_rna(dna):
    rna = ''
    translations = {'G':'C',
                    'C':'G',
                    'T':'A',
                    'A':'U'}
    for nuc in dna:
        rna += translations[nuc]
    return rna
