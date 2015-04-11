from string import maketrans

def to_rna(dna):
    """Converts a DNA strand into its equivalent RNA strand."""

    convert_table = maketrans("GCTA", "CGAU")
    
    return dna.translate(convert_table)
