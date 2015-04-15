def to_rna(strand):
    """Returns the RNA complement of a DNA strand"""

    # Store the DNA -> RNA mapping within a dict
    translate = { 'G':'C', 'C':'G', 'T':'A', 'A': 'U' }

    # Iterate over the strand, mapping each character, and return the joined result
    return ''.join([translate[c] for c in strand])
    
    
