#
# Calculate Hamming Distance
#

def distance(dna1, dna2):

    list1 = list(dna1)
    list2 = list(dna2)

    if len(list1) != len(list2):
        return Error
    else:
        diff = [i for i, j in zip(list1, list2) if i != j]
        return len(diff)
