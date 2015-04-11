def slices(number, length):
    result = []
    if len(number) < length or length == 0:
        raise ValueError("Invalid slice")

    for start in range(0, len(number) - length + 1):
        print(number[start:start+length])
        result.append([int(s) for s in list(number[start:start+length])])
    return result
