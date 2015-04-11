complements = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(string):
    result = ""
    for n in string:
        result += complements[n]
    return result
