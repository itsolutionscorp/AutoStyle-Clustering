import re

def to_rna(dna,
           _re=re.compile('.'),
           _tr={'A': 'U', 'C': 'G', 'G': 'C', 'T': 'A'}):
    return _re.sub(lambda m: _tr[m.group(0)], dna)
