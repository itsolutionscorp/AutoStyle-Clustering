def slices(string, length):
    if length > len(string) or length < 1:
        raise ValueError
    else:
        return [[int(j) for j in string[i:i+length]] for i in range(len(string) + 1 - length)]
