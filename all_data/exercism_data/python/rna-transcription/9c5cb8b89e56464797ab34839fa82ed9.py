def to_rna(dna_strand):
    transcription = {'A':'U', 'C':'G', 'G':'C', 'T':'A'}
    return ''.join(transcription[nucleotide] for nucleotide in dna_strand)
            
