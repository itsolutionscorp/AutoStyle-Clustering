def to_rna(dna):
    #Create temporary blank list and reference table
    rna = []
    lookup = {'G':'C','C':'G','T':'A','A':'U'}
    #for each nucleotide in the string, lookup the transcription and append
    for n in dna:
        rna.append(lookup[n])
    #return the transcribed string
    return(''.join(rna))
    
