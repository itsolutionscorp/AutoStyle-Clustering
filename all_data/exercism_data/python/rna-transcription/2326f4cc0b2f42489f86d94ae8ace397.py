def to_rna(dna):
    def transcribe(nucleotide):
        if nucleotide == "G":
            return "C"
        if nucleotide == "C":
            return "G"
        if nucleotide == "T":
            return "A"
        if nucleotide == "A":
            return "U"
    rna = ""
    for nucleotide in dna:
        rna += transcribe(nucleotide)
    return rna
