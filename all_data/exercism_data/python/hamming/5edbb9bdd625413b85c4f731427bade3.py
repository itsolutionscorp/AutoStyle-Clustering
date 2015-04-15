# Hmm, the python tests leave out the issue when the strands are not of equal lengths
# The Javascript exercise tested to ensure that the strands were only compared as far as
# the shortest strand
# Without this requirement, it would be far cleaner to use enumerate on strand a and compare.

def distance(strand_a, strand_b):
    shorter_length = len(strand_a) if len(strand_a) < len(strand_b)  else len(strand_b)
    hamming_distance = 0
    for index in range(0, shorter_length):
        if strand_a[index] != strand_b[index]:
            hamming_distance += 1

    return hamming_distance
