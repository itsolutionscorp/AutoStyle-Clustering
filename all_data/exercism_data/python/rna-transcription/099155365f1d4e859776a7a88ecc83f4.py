import string

valid_chars = 'ACGT'
mapping = string.maketrans(valid_chars,'UGCA')

def to_rna(dna_strand):
    if isinstance(dna_strand, basestring):
        if all(c in valid_chars for c in dna_strand):
            return dna_strand.translate(mapping)
        else:
            return None
    else:
        return None
