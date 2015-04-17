def slices(string, size):
    if not size or size > len(string):
        raise ValueError
    slices = []
    start = 0
    end = size
    numbers = [int(x) for x in list(string)]
    string_length = len(string)
    while end <= string_length:
        slices.append(numbers[start:end])
        start += 1
        end += 1
    return slices
