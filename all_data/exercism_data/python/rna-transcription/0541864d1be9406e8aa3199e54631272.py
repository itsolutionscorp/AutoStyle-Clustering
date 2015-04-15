def to_rna(original_dna):
    inp = "GCTA"
    outp = "CGAU"
    trans_table = str.maketrans(inp, outp)
    return original_dna.translate(trans_table)
