import re

def to_rna(sequence):
    sequence = re.sub('A', 'U', sequence)
    sequence = re.sub('T', 'A', sequence)
    sequence = re.sub('C', 'B', sequence)
    sequence = re.sub('G', 'C', sequence)
    sequence = re.sub('B', 'G', sequence)

    return sequence
