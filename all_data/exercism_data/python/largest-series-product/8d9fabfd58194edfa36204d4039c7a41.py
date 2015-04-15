def slices(string, n):
    if n > len(string) or n <= 0:
        raise ValueError('No such slice!')

    ilst = [int(s) for s in string]
    return [list(ilst[i:n + i]) for i in range(len(string) - n + 1)]

def largest_product(line, n):
    return 0
