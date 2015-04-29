def distance(strand1, strand2):
    list1 = list(strand1)
    list2 = list(strand2)
    length = len(list1)
    mutation = [list1[i] != list2[i] for i in range(length)]
    return sum(mutation)
