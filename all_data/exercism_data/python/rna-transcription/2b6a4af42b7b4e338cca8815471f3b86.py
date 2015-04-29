def to_rna(string):
    rnaMap = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U'}
    return ''.join([rnaMap[i.upper()] for i in string])
