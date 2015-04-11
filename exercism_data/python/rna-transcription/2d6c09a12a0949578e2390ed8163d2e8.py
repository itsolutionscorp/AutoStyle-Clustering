dna_to_rna = {dna: rna for (dna, rna) in zip('GCTA', 'CGAU')}

def to_rna(dna_sequence):
    return ''.join(dna_to_rna[base] for base in dna_sequence.upper())
