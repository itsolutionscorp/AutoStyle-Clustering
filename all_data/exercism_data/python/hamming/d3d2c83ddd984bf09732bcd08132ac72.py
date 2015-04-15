# Get hamming distance = number of exchanged nucleotids.
# Only for DNA strands of the same length.


def distance(dna1, dna2):

    # First check if dna strands have same length?
    # Maybe throw an exception here?

    count = sum(1 for nuc1, nuc2 in zip(dna1, dna2) if nuc1 != nuc2)

    return count
