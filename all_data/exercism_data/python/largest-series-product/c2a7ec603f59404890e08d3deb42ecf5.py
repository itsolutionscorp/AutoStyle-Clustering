def largest_product(full_set, lengths):
    """ I will improve this method. Simply first version
    """
    chunk_list = slices(full_set, lengths)
    sums = []

    for sequence in chunk_list:
        temp = 1
        for item in sequence:
            temp *= item
        sums.append(temp)
    return max(sums)

def slices(full_set, lengths):
    set_len = len(full_set)
    if (lengths > set_len) or (lengths <= 0 and set_len != 0):
        raise ValueError("The desired slice length is invalid")

    slices = []

    for item in range(0, set_len-lengths+1):
        slices.append([int(x) for x in full_set[item:item+lengths]])

    return slices
