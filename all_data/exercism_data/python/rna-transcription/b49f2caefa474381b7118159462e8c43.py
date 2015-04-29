def to_rna(dna):
    conversion_table = {'G' : 'C', 'C':'G', 'T':'A', 'A':'U'}

    return "".join(conversion_table[nucleotide] for nucleotide in dna if nucleotide in conversion_table)
