def to_rna(dna):
    rna_transcription = {'G':'C','C':'G','T':'A','A':'U'}
    return ''.join([rna_transcription[nucleotide] for nucleotide in dna])
