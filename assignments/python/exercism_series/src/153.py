def slices(series, n):
    if len(series) < n or n <= 0: raise ValueError("")
    return [ [int(d) for d in series[i:i+n] ] for i in range(0,len(series)+1-n)]
