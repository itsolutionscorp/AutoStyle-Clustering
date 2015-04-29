DNA_TO_RNA = str.maketrans("GCTA", "CGAU")

def to_rna(seq):
    return seq.translate(DNA_TO_RNA)
