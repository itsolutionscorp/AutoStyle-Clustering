# RNA Transcription exercism

def to_rna(dna):
    dna = dna.replace('A','U') # Swap A for U
    dna = dna.replace('T','A') # Swap T for A
    
    # We will store the string as a list
    nucleotides = []
    for n in dna:
        nucleotides.append(n)
    
    # We go through each nucleotide and change C to G and G to C
    for i in range(len(nucleotides)):
        changed = False
        if nucleotides[i] == 'C':
            nucleotides[i] = 'G'
            changed = True
        elif nucleotides[i] == 'G' and not changed:
            nucleotides[i] = 'C'
    
    # We now put the list of nucleotides into a string
    rna=""
    for n in nucleotides:
        rna += n
    return rna
