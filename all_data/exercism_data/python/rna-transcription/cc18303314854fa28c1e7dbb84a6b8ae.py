def to_rna(input):
    table = str.maketrans({'G':'C', 'C':'G', 'T':'A', 'A':'U'})

    return input.translate(table)
