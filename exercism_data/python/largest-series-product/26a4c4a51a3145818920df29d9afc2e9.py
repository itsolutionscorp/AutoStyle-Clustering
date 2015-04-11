def slices(series, width):
    if width > len(series):
        raise ValueError('baaark')
    chunks = [list(series[a:a+width]) for a in range(len(series)-width+1)]
    numchunks = [[int(element) for element in chunk] for chunk in chunks]
    return numchunks

def largest_product(series, width):
    numchunks = slices(series, width)
    maxval = 0
    for nchunk in numchunks:
        val = 1
        for element in nchunk:
            val = val * element
        if val > maxval:
            maxval = val
    return maxval             
