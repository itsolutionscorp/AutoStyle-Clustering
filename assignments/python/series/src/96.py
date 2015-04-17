def slices(array, chunk):
    if chunk > len(array) or chunk <= 0:
        raise ValueError
    ret_array = []
    for i in range(len(array) + 1 - chunk):
        tmp_array = []
        for j in range(chunk):
            tmp_array.append(int(array[i+j]))
        ret_array.append(tmp_array)
    return ret_array
