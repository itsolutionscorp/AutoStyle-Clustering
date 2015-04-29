def encode(acid):
    """
    Returns appropriate RNA acid for given DNA acid.
    """
    if acid == "G":
        return "C"
    elif acid == "C":
        return "G"
    elif acid == "T":
        return "A"
    elif acid == "A":
        return "U"


def to_rna(dnaString):
    """
    Encodes given DNA string to RNA string
    """
    return "".join(encode(acid) for acid in dnaString)
