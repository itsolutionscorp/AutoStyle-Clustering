import itertools

def hamming(dna0, dna1):
    aggregate = itertools.izip_longest(dna0,dna1,'F')
    count = 0
    for item in aggregate:
        if item[0] != item[1]:
            count+=1
    return count
