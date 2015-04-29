DNA_TO_RNA_TRANSCRIPTION = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(dna):
    return ''.join(
        [DNA_TO_RNA_TRANSCRIPTION.get(nucleotide, '') for nucleotide in dna]
    )
