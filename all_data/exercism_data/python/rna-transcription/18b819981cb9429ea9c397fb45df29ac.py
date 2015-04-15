def to_rna(dna):
    k = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U",
    }
    rna = ""
    for n in dna:
        try:
            rna += k[n]
        except KeyError:
            print "Invalid DNA nucleotide: %s" % n

    return rna


if __name__ == "__main__":
    print to_rna("G")
    print to_rna("C")
    print to_rna("T")
    print to_rna("A")
