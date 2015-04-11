#
# Hamming Distance Detector
#

def distance(rna1, rna2):

    hamming_distance = 0

# for each character in rna1
    for char in xrange(0, len(rna1)):
# if char in rna1 is not equal to rna2
        if rna1[char] is not rna2[char]:
# increment the distance counter
            hamming_distance += 1
# output the distance
    return hamming_distance
