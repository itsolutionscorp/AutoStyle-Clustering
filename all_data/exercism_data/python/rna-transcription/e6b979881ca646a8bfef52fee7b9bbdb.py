def to_rna(strand):
    word_map = {'G': 'c', 'C': 'g', 'T': 'a', 'A': 'u',}
    for dna, rna in word_map.items():
        strand = strand.replace(dna, rna)
    return strand.upper()
