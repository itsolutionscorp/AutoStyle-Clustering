TRANSCRIPTION = { 'A': 'U', 'C' : 'G', 'G' : 'C', 'T' : 'A' }

def to_rna( dna ):
    """ transcribes dna to rna
    nucleotides in DNA are adenine 'A', cytosine 'C', guanine 'G', thymine 'T'
    nucleotides in RNA are adenine 'A', cytosine 'C', guanine 'G', uracil 'U'

    complements are:
    * 'G' -> 'C'
    * 'C' -> 'G'
    * 'T' -> 'A'
    * 'A' -> 'U'
    """

    if len( dna ) == 1:
        if dna in TRANSCRIPTION:
            return TRANSCRIPTION[ dna ]
    else:
        firstNucleotide = dna[ 0 ]
        endOfDnaSequence = dna[ 1: ]

        return to_rna( firstNucleotide ) + to_rna( endOfDnaSequence )

    return ""
