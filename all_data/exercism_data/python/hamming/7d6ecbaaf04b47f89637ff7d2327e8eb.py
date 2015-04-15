def hamming(strandOne, strandTwo):
    # Setup our counter
    diffCounter = 0
    # min() and max() return the same string if strings are equal
    # size, so we'll just execute if lengths don't match
    if len(strandOne) != len(strandTwo):
        longestStrand = max(strandOne, strandTwo, key=len)
        shortestStrand = min(strandOne, strandTwo, key=len)
    # In case of same length strings, we'll cheat and call one
    # arbitrarily longer than two
    else:
        longestStrand = strandOne
        shortestStrand = strandTwo

    for i, letter in enumerate(longestStrand):
        try:
            secondLetter = shortestStrand[i]
        except IndexError:
            # We could also for example do diffCounter++
            # and then 'continue' out of this loop iteration
            secondLetter = ""

        if letter != secondLetter:
            diffCounter = diffCounter + 1

    return diffCounter
