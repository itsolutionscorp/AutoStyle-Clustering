# Hanley Washington's Hamming Python Exercism, iteration 2

def distance(dna1,dna2):
    if len(dna1) != len(dna2): # Return 0 for non-comparable DNA strands
        raise ValueError('hamming distance can only be calculated for strings of equal length')
    else:
        ham = 0 # The hamming distance will be stored in ham
        for i, j in zip(dna1,dna2):
            if i != j: # When nucleotides differ we increase ham by 1
                ham += 1
        return ham
