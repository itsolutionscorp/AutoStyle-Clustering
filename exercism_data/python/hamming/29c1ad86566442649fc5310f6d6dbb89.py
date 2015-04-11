# calculate the Hamming difference between two DNA strands
# The Hamming distance is only defined for sequences of equal length.


def distance(s1, s2):
    if len(s1) == len(s2):
        diff = 0
        for num in range(len(s1)):
            if s1[num] != s2[num]:
                diff += 1
        return diff
    else:
        print "The length of the strands do not match."
        return False
