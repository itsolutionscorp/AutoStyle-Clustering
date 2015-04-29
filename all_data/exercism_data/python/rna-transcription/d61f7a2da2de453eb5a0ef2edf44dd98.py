def to_rna(strand):
    """
    (str)->str

    given a DNA strand, returns its RNA complement
    """
    rnastrand = ""
    for nucleo in strand:
        if nucleo == 'A':
            rnastrand += 'U'
        elif nucleo == 'T':
            rnastrand += 'A'
        elif nucleo == 'C':
            rnastrand += 'G'
        else:#if nucleo == 'G':
            rnastrand += 'C'
    return rnastrand
