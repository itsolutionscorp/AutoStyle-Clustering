__author__ = 'emiller42'
def slices(sequence, length):
    if length == 0:
        raise ValueError("Length argument must be > 0")
    if len(sequence) < length:
        raise ValueError("Length argument cannot be larger than the length of the sequence")
    list_sequence = list(sequence)
    return_list = []
    for i in range(0, len(sequence) - length + 1):
        return_item = []
        for j in range(i, i + length):
            return_item.append(int(list_sequence[j]))
        return_list.append(return_item)
    return return_list
