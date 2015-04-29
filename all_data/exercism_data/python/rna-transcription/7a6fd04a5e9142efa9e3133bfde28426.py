def to_rna(dna):
    rna_strand = ''
    conversion = { 'G' : 'C', 'C' : 'G', 'T': 'A', 'A' : 'U' }
    for letter in dna:
        rna_strand += conversion[letter]
    return rna_strand
