dnatorna = {"G" : "C",
            "C" : "G",
            "T" : "A",
            "A" : "U"}

def to_rna(dna):
    rna = []

    for letter in dna:
        rna.append(dnatorna[letter])

    rna = "".join(rna)
    return rna
