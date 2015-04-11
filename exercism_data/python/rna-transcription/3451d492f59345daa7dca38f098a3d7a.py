__author__ = 'Micha'


def to_rna(dna):
    dna.upper()
    rna = ""
    for dna_elem in dna:
        if dna_elem == 'G':
            rna += 'C'
        elif dna_elem == 'C':
            rna +='G'
        elif dna_elem == 'T':
            rna +='A'
        elif dna_elem == 'A':
            rna += 'U'
        else:
            print("Wrong enter")
    return str(rna)
