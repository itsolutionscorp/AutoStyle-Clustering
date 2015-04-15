"""
hamming module
"""


def hamming(s1, s2):
    """calculating the hamming weight of s1 and s2"""
    weight = abs(len(s1)-len(s2))
    if len(s1) < len(s2):
        s1, s2 = s2, s1
    for i in range(len(s2)):
        weight += not s1[i] == s2[i]
    return weight
