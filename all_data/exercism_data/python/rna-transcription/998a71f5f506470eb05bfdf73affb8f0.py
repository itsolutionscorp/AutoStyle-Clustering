def to_rna(dna):
    transcription = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }

    rna = "".join([transcription[nucleotide] for nucleotide in dna])
    return rna
