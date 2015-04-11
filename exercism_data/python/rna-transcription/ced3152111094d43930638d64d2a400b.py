def to_rna(strand):
    return "".join(map(lambda x: {'G':'C','C':'G','T':'A','A':'U'}[x], strand))
