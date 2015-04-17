def slices(string, size):
    if size>len(string) or not size:
        raise ValueError
    arrays = []
    for start in range(len(string)-size+1):
        array = []
        for num in string[start:start+size]:
            array.append(int(num))
        arrays.append(array)
    return arrays
