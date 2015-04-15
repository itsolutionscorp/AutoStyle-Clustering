""" Returns Hamming difference between two DNA strands s1 & s2
"""
def hamming(s1, s2):
    len1 = len(s1)
    len2 = len(s2)
    hamming_diff = 0
    # First strand s1 is shorter, append X for each
    # extra nucleotide in s2
    if len1 > len2:
        for i in range(len1 - len2):
            s2 += 'X'
    # Second strand s2 is shroter, append X for each
    # extra nucleotide in s1
    elif len2 > len1:
        for i in range(len2 - len1):
            s1 += 'X'
    # Both strands are empty, so return 0
    elif len1 == 0:
        return 0
    # Compare nucleotides at each index
    # Increment Hamming difference for each mismatch
    for i in range(len(s1)):
        if s1[i] != s2[i]:
            hamming_diff += 1

    return hamming_diff

