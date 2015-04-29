def slices(full_set, lengths):
    set_len = len(full_set)
    if lengths > set_len or lengths <= 0:
        raise ValueError

    number_list = [int(x) for x in full_set]

    slices = []

    for item in range(0, set_len-lengths+1):
        slices.append(number_list[item:item+lengths])

    return slices
