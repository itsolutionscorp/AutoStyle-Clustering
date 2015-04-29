rna = {'C':'G',
        'G':'C',
        'A':'U',
        'T':'A'
        }

def to_rna(rna_string):
    return "".join([rna[x] for x in rna_string])
