def slices(string, n):
    ilst = [int(s) for s in string]
    return [list(ilst[i:n+i]) for i in range(len(string) - n + 1)]
