def slices(string, length):
    numberslist = [int(x) for x in string]
    if not 1 <= length <= len(numberslist):
        raise ValueError("Slice length issue")
    return [numberslist[i:i + length] for i in range(len(numberslist)
        - length + 1)]
