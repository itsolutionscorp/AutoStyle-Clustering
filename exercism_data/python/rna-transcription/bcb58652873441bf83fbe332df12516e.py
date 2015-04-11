def to_rna(DNA):
    RNA=""
    for nucleotid in DNA:

        if nucleotid == "A" or nucleotid == "a":
            RNA += "U"

        if nucleotid == "G" or nucleotid == "g":
            RNA += "C"

        if nucleotid == "C" or nucleotid == "c":
            RNA += "G"

        if nucleotid == "T" or nucleotid == "t":
            RNA += "A"

    return RNA
