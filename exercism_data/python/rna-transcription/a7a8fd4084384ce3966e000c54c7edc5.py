def to_rna(dna_string):
    """Simple function to translate a DNA string to its RNA complement

    """
    rna_result = ""
    for char in dna_string:
        if char == 'G':
            rna_result += 'C'
        elif char == 'T':
            rna_result += 'A'
        elif char == 'C':
            rna_result += 'G'
        elif char == 'A':
            rna_result += 'U'
    return rna_result
