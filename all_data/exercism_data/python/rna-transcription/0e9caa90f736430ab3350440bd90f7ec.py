def to_rna(dna):
    conversionMap = {   'G' : 'C' ,\
                        'C' : 'G',\
                        'T' : 'A',\
                        'A' : 'U'}
    return ''.join([conversionMap[x] for x in dna])
