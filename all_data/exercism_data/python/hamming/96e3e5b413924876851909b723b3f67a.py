# 
# exercism.io code challenge
# Calculates the Hamming difference between two DNA strands (a and b)
#
# Joshua Ferdaszewski
#

def distance(a, b):
    
    # DNA strands must be of the same length!
    if len(a) != len(b):
        raise ValueError("DNA strands not the same length!")

    return sum(1 for i in range(len(a)) if a[i] != b[i])
