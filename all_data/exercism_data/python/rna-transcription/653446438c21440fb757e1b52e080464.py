def to_rna(dna):

    results = ""
    converter = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    for c in dna:
        results = results + (converter[c])

    return results
