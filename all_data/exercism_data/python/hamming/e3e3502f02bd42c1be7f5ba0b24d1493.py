#*-* coding: utf-8 *-*
def distance(DNA1, DNA2):
    hamming = 0
    for x in xrange(len(DNA1)): 
        Nuc_1 = list(DNA1).pop(x)
        Nuc_2 = list(DNA2).pop(x)
        if Nuc_1 != Nuc_2:
            hamming +=1
    return hamming
if __name__ == '__main__':
    input = raw_input()
    print hamming(input)
