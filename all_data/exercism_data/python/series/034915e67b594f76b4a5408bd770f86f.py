def slices(number, length):
    return_list = [map(int, list(number[int(i) : int(i) + length])) for i in range(len(number)) if len(number[int(i) : int(i) + length]) == length]

    if length > len(number) or length == 0:
        raise ValueError('There are no results')
    else:
        return return_list
