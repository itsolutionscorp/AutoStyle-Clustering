def to_rna(dnaseq):
# convert dna sequence to rna using  a transcription (DNA->RNA) 
# dictionary over each element of the dna sequence
    try:
        dnaseq = dnaseq.upper()
        transcription = dict(zip(['A','C','G','T'], 
                                 ['U','G','C','A']))
        rna = [transcription[nucleotide] for nucleotide in dnaseq]
        return ''.join(rna)
    except KeyError:
        print ("Argument must be a string of letters "
               "'A', 'C', 'G', and 'T'.")
        
                        
