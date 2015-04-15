def to_rna(strand):
    dict = {'G': 'C', 'C':'G', 'T': 'A', 'A': 'U'}
    result = ""
    for c in strand:
        result += dict[c]
    return result
