class InvalidDNASequenceError(Exception):
    pass


def to_rna(dna_string):
    rna_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna_result = ''
    try:
        for char in dna_string:
            rna_result += rna_dict[char]
    except KeyError:
        raise InvalidDNASequenceError('Invalid DNA sequence given')
    return rna_result
