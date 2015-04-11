def to_rna(string):
    convert = {'G':'C','C':'G','T':'A','A':'U'}
    return ''.join(convert[nuc] for nuc in string)
