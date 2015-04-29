def slices(str, seqLen):

    results = []

    # Throw ValueErrors when not passed faulty arguments
    if seqLen > len(str):
        raise ValueError('The len argument is shorter than the series')
    elif seqLen < 1:
        raise ValueError('The len argument is smaller than 1')

    CompleteList = [int(i) for i in str]

    for i in xrange(0, len(CompleteList), 1):
        if len(CompleteList[i:i + seqLen]) == seqLen:
            results.append(CompleteList[i:i + seqLen])

    return results
