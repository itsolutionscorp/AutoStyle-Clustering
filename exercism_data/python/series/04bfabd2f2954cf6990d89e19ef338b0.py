def slices(series,length):

    if length < 1 or length > len(series):
        raise ValueError("You gettin' the AIDS now.")
    results = []
    for i in range(len(series)-length+1):
        results.append([int(x) for x in series[i:i+length]])
    return results
