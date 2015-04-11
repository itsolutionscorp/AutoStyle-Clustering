def to_rna(nucleotide):

    nucleotides = {"G":"C","C":"G","T":"A","A":"U"}
    rna = ""

    for letter in nucleotide:
        rna+=nucleotides[letter]

    return rna
