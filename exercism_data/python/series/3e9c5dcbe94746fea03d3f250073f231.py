def slices(string, number):
    i = 0
    output = []
    if number < 1 or number > len(string):
        raise ValueError
    while i < len(string) - number + 1:
        output.append([])
        k = i
        j = 0
        while j < number:
            output[i].append(int(string[k]))
            j += 1
            k += 1
        i += 1
    return output
