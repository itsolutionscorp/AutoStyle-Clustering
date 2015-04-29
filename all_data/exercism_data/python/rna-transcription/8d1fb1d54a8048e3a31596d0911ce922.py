transcribe = {'C': 'G', 'G': 'C', 'T': 'A', 'A': 'U'}

def to_rna(sequence):
    dna = []
    for nucleotide in sequence:
        dna.append(transcribe.get(nucleotide, nucleotide))

    return "".join(dna)
