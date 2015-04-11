from itertools import izip_longest

def hamming(first, second):
    """returns Hamming distance of 2 DNA strands"""

    #create a list of tuples from both strands
    comparison = list(izip_longest(first, second))
    counter = 0

    #count differences
    for pair in comparison:
        if pair[0] != pair[1]:
            counter +=1 

    return counter
