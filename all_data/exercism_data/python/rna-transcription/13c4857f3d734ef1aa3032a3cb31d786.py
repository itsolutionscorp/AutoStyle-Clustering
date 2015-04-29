def to_rna(dna):

    translation = {'G': 'C',
                   'C': 'G',
                   'T': 'A',
                   'A': 'U'}

    # Iterate over the nucleotides in the dna sequence, translating them to rna
    # nucleotides and concatenating them into an rna sequence.
    return reduce(lambda rna, nucleotide: rna + translation[nucleotide], 
                  dna, 
                  '')
