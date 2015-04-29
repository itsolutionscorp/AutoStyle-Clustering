

def to_rna(dna_strand):

    nucleotide_mapping = { 'G':'C', 'C':'G', 'T':'A', 'A':'U' }
    transcribed_rna_strand = [nucleotide_mapping[nucleotide] for nucleotide in dna_strand]
    
    return ''.join(transcribed_rna_strand)
    
