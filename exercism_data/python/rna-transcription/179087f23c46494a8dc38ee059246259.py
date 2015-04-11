def to_rna(strand):
    dict = {}
    dict = {'G':'C','C':'G','T':'A','A':'U'}
    result = ""
    for i in strand:
        result = result + dict[i]
    return result
