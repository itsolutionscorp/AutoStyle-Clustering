#-------------------------------------------------------------------------------
# Name:        dna.py
# Purpose:     Given a DNA strand, the function returns its
#              RNA complement (per RNA transcription)
#
# Author:      Mihaela
#
# Created:     27.09.2014
#-------------------------------------------------------------------------------

def to_rna(sequence):
    answer = ''
    for element in sequence:
        if element == 'G':
           answer = answer+'C'
        elif element == 'C':
             answer = answer+'G'
        elif element == 'T':
             answer = answer+'A'
        elif element == 'A':
             answer = answer+'U'
        else:
             print '''Not a valid nucleotide,
                   it shall not be taken into
                   consideration'''
    return answer
