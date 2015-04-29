def to_rna(dna):
    rna = ""
    for letter in dna:
        rna += conversion[letter]
    return rna

conversion = { 'G' : 'C',
               'C' : 'G',
               'T' : 'A',
               'A' : 'U'
    }
