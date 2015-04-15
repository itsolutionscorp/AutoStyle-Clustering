def slices(series, n):
    __validate(series, n)
    parsed = __integerize(series)
    return [parsed[i:i+n] for i in range(0, len(parsed)) if len(parsed[i:i+n]) == n]

def __validate(series, n):
    if n > len(series) or n == 0:
        raise ValueError("Invalid slice length for this series: " + str(n))

def __integerize(series):
    return map(int, list(series))
