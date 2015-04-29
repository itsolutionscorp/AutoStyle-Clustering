__author__ = 'jeffmarkey'

def to_rna(dna):
    dict = { 'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U' }
    content=''
    for line in dna:
        content+=dict[line]
    return content
