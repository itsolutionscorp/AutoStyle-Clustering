import re
import string

mapping = string.maketrans('ACGT','UGCA')

def to_rna(dna_strand):
    if isinstance(dna_strand, basestring):
        if re.match('[ACGT]+', dna_strand):
            return dna_strand.translate(mapping)
        else:
            return None
    else:
        return None
