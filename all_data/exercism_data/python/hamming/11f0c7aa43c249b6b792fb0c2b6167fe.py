
def hamming (set1,set2):
    hamming_ct = 0
    if len(set1) > len(set2):
        length = len(set1)
        for x in range(length - len(set2)):
            set2 += ' '
    else:
        length = len(set2)
        for x in range(length - len(set1)):
            set1 += ' '
    for x in range(length):
        if set1[x] != set2[x]:
            hamming_ct += 1
    return hamming_ct
            
