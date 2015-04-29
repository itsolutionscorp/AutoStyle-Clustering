def to_rna(dna):
    dna_translator = str.maketrans('GCTA', 'CGAU')
    dna = dna.translate(dna_translator)
    return dna
