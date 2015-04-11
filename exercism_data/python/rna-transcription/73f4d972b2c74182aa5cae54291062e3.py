conversion_dict = {

'G' : 'C',
'C' : 'G',
'T' : 'A',
'A' : 'U'

}

def to_rna(dna):
    converted = ""
    for nucleotide in dna:
        converted += conversion_dict[nucleotide]
    return converted
