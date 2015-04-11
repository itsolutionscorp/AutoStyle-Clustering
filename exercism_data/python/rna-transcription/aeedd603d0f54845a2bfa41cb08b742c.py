def to_rna(strand):
    if isinstance(strand, str):
        rna = ""
        for l in strand:
            if l.upper() == "C":
                rna += "G"
            elif l.upper() == "G":
                rna += "C"
            elif l.upper() == "T":
                rna += "A"
            elif l.upper() == "A":
                rna += "U"
            else:
                raise Exception, "Wrong nucleotides, check given strand"
    else:
        raise TypeError, "Strand can be only a string."

    return rna
