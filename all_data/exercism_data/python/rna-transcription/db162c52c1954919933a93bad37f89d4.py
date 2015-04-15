def to_rna(dna_string):
    return ''.join([{'G':'C','C':'G','T':'A','A':'U'}[x] for x in dna_string])
