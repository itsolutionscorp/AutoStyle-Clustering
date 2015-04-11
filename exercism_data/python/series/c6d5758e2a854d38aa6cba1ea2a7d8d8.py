def slices(number, size):
    if size > len(number) or size <= 0:
        raise ValueError

    list_of_slices = []
    for i in range(0, len(number)-size+1):
        list_of_slices.append(map(int, list(number[i:size+i])))

    return list_of_slices
