def to_rna(dna):
    result = ""
    for char in dna:
        if char == 'G':
            result += 'C'
        elif char == 'C':
            result += 'G'
        elif char == 'T':
            result += 'A'
        elif char == 'A':
            result += 'U'
    return result
