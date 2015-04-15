def to_rna(dna):
    """Given a DNA strand, returns its RNA complement (per RNA transcription)"""
    rna_transcription_map = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    rna = ""
    for nucleotide in dna:
        rna += rna_transcription_map[nucleotide]
    return rna
