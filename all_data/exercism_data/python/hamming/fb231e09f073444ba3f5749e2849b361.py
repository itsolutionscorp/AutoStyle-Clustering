#
# Returns the Hamming distance of two DNA strings.
#

def distance(seq1, seq2):
    seqs = [(seq1[i], seq2[i]) for i in range(len(seq1))]

    return sum(a != b for a, b in seqs)
