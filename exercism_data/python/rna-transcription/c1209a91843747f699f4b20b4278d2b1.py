def to_rna(dna_strand):

    nucleos = [letter for letter in dna_strand] 

    for position, nucleotide in enumerate(nucleos):
        if nucleotide == 'G':
            nucleos[position] = 'C'
        elif nucleotide == 'C':
            nucleos[position] = 'G'
        elif nucleotide == 'T':
            nucleos[position] = 'A'
        elif nucleotide == 'A':
            nucleos[position] = 'U'
   

    return ''.join(nucleos)
