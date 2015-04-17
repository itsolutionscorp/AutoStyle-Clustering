def slices(string, number):
    if number < 1 or number > len(string):
        raise ValueError
    stringintlist = [int(s) for s in string]
    output = []
    for i in range(len(string) - number + 1):
            output.append(list(stringintlist[i:i + number:]))
    return output
