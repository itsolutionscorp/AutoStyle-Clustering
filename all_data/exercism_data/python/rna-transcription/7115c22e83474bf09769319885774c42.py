RNAKey = {
    'G':'C',
    'C':'G',
    'T':'A',
    'A':'U'
    }

def to_rna(DNA):
    RNA = ''
    for nucleotide in DNA:
        RNA += RNAKey[nucleotide]

    return RNA
