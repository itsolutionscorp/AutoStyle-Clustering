def to_rna(dna):
    transcriptions = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    nucleotides = [transcriptions[nucleotide] for nucleotide in dna]
    return ''.join(nucleotides)
