
'''
Get the difference of the string length of two string as a default
Hamming distance. Use zip() to compare and count the hamming distance of
strands having same length.
'''

def hamming(dna_strand_1, dna_strand_2):
  
  return sum(a != b for a, b in zip(dna_strand_1,dna_strand_2)) + abs(len(dna_strand_1) - len(dna_strand_2))
