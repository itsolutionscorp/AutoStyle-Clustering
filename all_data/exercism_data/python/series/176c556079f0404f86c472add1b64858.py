def slices(full_set, lengths):
    set_len = len(full_set)
    if lengths > set_len or lengths <= 0:
        raise ValueError("The desired slice length is invalid")

    slices = []

    for item in range(0, set_len-lengths+1):
        slices.append([int(x) for x in full_set[item:item+lengths]])

    return slices
