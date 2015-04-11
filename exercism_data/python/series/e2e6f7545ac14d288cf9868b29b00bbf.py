def slices(series, n):
    if len(series) < n or n <= 0: raise ValueError("")
    result = []
    for i in range(0,len(series)+1-n):
        result.append( [int(a) for a in series[i:i+n] ] )    
    return result
