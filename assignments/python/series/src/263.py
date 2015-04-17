def slices(series, length):
    if length < 1:
        raise ValueError("Non-positive lengths not allowed.")
    if length > len(series):
        raise ValueError("Lengths longer than series not allowed.")
    return [list(map(int,series[i:i+length])) for i in range(len(series)-length+1)]
