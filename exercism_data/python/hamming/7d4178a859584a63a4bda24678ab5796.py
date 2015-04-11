def hamming(strand1, strand2):

    # create your lists
    list1 = list(strand1)
    list2 = list(strand2)

    # list2 is always equal to or greater than list1
    if len(list2) < len(list1):
        list1, list2 = list2, list1

    # count differences by checking length differences first
    count = len(list2) - len(list1)

    # count the remaining differences between list1 and list2
    for i in range(len(list1)):
        if list1[i] != list2[i]:
            count = count + 1
    return count
