def slices(seq, n):
    if n > len(seq) or n < 1:
        raise ValueError("Impossible to find series of length %d in sequence of length %d" %(n, len(seq)))
    #series = []
    seq = list(map(int, seq))
    #for i in range(len(seq)-n+1):
    #    series.append(seq[i:i+n])
    #return series
    return [seq[i:i+n] for i in range(len(seq)-n+1)]
