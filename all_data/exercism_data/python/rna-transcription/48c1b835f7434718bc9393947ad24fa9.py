__author__ = 'samuelbarthelemy'


def to_rna(dna):

    dna = dna.strip()
    rna = ""
    for letter in dna:
        if letter == "G":
            rna += "C"
        elif letter == "C":
            rna += "G"
        elif letter == "T":
            rna += "A"
        else:
            rna += "U"


    return rna
