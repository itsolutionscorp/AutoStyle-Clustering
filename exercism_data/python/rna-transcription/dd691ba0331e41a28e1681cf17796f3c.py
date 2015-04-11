#
#  Give the rna transcription from the given dna
#

import sys

dna2rna = {'G':'C','C':'G','T':'A','A':'U'}

def to_rna(strand):
    strand = strand.upper()
    properDna = set(dna2rna.keys())
    if properDna >= set(strand):
       rna = ''
       for amino in strand:
           rna+=(dna2rna[amino])
       return rna
    else:
        print "The string entered does not properly represent a dna sequence."

if __name__ == '__main__':
    print to_rna(sys.argv[1])
