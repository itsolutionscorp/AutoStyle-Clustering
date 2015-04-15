
##TEENAGE MUTANT CODING PYTHONS! wait.

"""#So... turns out that I can't "assume that only sequences of equal length will be passed to your hamming
distance function.". Which, I gotta say, was a little annoying, but it was hardly a bump in the road.
Making adjustments is just as important as anything else. The problem would
have been too easy slapping an assertion in front of the function to ignore different lengths
"""
def hamming(sequence1, sequence2):
    distance = abs(len(sequence1)-len(sequence2)) #different length sequences inherently have a minimum distance based in that length difference
    shortLength = min(len(sequence1), len(sequence2)) #iterate using short length. long length will err out of range
    
    for i in range(shortLength):
        if (sequence1[i] != sequence2[i]):
            distance += 1
    return distance
