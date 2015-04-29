def to_rna(strand):
    """
    (str)->str

    given a DNA strand, returns its RNA complement
    """
    return strand.translate(str.maketrans("ATCG", "UAGC"))
