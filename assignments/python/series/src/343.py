def slices(series,n):
    length = len(series)
    if n > length or n == 0:
        raise ValueError ("requested series too long or zero")
    else:
        intlist = list(map(int, series))
        return list(intlist[i:i+n] for i in range(length-n+1))
