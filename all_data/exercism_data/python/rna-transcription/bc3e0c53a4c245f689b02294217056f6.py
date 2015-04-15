import string as s

def to_rna(strand):

    # Create the translation table
    tb = s.maketrans('GCTA', 'CGAU' )

    # Return translated string
    return strand.translate(tb)
