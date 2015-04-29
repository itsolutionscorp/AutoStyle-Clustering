from string import maketrans

key = maketrans("GCTA","CGAU")

def to_rna(string): return string.translate(key)
