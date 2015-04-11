def to_rna(dna_strand):
    transcribe = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return "".join([transcribe[nucleotide] for nucleotide in dna_strand])
