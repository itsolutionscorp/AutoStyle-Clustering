def to_rna(sequence):
    dna_to_rna = {
        'c' : 'g',
        'g' : 'c',
        'a' : 'u', 
        't' : 'a' 
    }

    rna_sequence = ""

    for i in list(sequence):
        rna_sequence += dna_to_rna[i.lower()]
    return rna_sequence.upper()
