def slices(string, num):
    sequences = []
    as_list = list(string)
    as_ints = [int(s) for s in as_list]
    if num > len(string) or num < 1:
        raise ValueError
    for i in range(len(string)):
        if len(string[i:]) >= num:
            sequences.append(as_ints[i:i+num])
    return sequences
