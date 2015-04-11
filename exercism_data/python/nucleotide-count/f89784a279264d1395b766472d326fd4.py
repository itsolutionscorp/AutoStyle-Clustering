__author__ = 'Norm'

def count(string, nucleotide):
    result = 0
    if nucleotide not in "ATCG":
        raise ValueError(nucleotide + " is not a valid character. Must be 'A', 'T', 'C' or 'G' ")
    for char in string:
        if char == nucleotide:
            result += 1
    return result

def nucleotide_counts(string):
    results = {'A': 0, 'T': 0, 'C': 0, 'G': 0}
    for char in string:
        results[char] += 1
    return results
