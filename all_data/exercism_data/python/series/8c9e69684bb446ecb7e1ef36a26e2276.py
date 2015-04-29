def slices(series,size):
    if size > len(series) or size == 0:
        raise ValueError
    return [map(int,series[i:i+size]) for i in range(0,len(series)-size+1)]
