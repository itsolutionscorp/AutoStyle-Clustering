#
# RNA Transcriber
#

def to_rna(dna):

# create output string
    output = ""

# for each char in the string, do the transcription
    for char in xrange(0, len(dna)):
        if dna[char] == 'G':
            output += 'C'
        elif dna[char] == 'C':
            output += 'G'
        elif dna[char] == 'T':
            output += 'A'
        elif dna[char] == 'A':
            output += 'U'

    return output
