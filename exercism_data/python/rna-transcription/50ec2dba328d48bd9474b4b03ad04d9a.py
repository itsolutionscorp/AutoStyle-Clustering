#
# RNA Transcriber
#

def to_rna(dna):

# create output string
    output = ""

# for each char in the string, do the transcription
    for char in dna:
        if char == 'G':
            output += 'C'
        elif char == 'C':
            output += 'G'
        elif char == 'T':
            output += 'A'
        elif char == 'A':
            output += 'U'

    return output
