import string

def hamming(dna1, dna2):
    count = 0
    ind = 0

    new = dna1.ljust(len(dna2),'X')
    new2 = dna2.ljust(len(dna1),'X')
 
    for i in new:
        if new[ind] != new2[ind]:
            count += 1
        ind+=1
    return count
