def slices(series, width):
    if width > len(series):
        raise ValueError('baaark')
    chunks = []
    numchunks = []
    b = 0
    for a in range(0, len(series)-width+1):
        chunks.append(list(series[a:a+width]))
    for chunk in chunks:
        nchunk = []
        for element in chunk:
            nchunk.append(int(element))
        numchunks.append(nchunk)
    return numchunks

def largest_product(series, width):
    if width > len(series):
        raise ValueError('baaark')
    numchunks = slices(series, width)
    maxval = 0
    for nchunk in numchunks:
        val = 1
        for element in nchunk:
            val = val * element
        if val > maxval:
            maxval = val
    return maxval             
