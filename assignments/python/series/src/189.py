def slices(value, slicelength):
    results = []
    result = []
    if slicelength == 0 or slicelength > len(value):
        raise ValueError('baaark')
    if slicelength == 1:
        numresults = len(value)
    else:    
        numresults = len(value[:-(slicelength-1)])
    for c in range(numresults):
        for a in range(slicelength):
            result.append(int(value[a+c]))
        results.append(result)
        result = []
    return results
