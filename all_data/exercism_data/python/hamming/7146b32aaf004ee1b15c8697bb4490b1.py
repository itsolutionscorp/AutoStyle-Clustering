__author__="cdances"

def distance( strand_1, strand_2 ):
    hamming=0 # The hamming distance
    N=min(len(strand_1),len(strand_2)); # Length of smaller strand
    for i in range(N):
        if( strand_1[i] != strand_2[i] ):
            hamming+=1 # Count the differences between the two strands
    return hamming
